package zdl.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zdl.blogsystem.Mapper.BlogInfoMapper;
import zdl.blogsystem.Mapper.UserInfoMapper;
import zdl.blogsystem.common.excepiton.BlogException;
import zdl.blogsystem.common.pojo.BlogInfo;
import zdl.blogsystem.common.pojo.UserInfo;
import zdl.blogsystem.common.pojo.request.LoginRequest;
import zdl.blogsystem.common.pojo.response.LoginResponse;
import zdl.blogsystem.common.pojo.response.UserInfoResponse;
import zdl.blogsystem.common.utils.JwtUtils;
import zdl.blogsystem.common.utils.SecurityUtil;
import zdl.blogsystem.service.BlogService;
import zdl.blogsystem.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource(name = "userInfoMapper")
    private UserInfoMapper mapper;

    @Resource(name = "blogInfoMapper")
    private BlogInfoMapper blogInfoMapper;

    @Resource(name = "blogServiceImpl")
    private BlogService blogService;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        //根据username查询用户
        UserInfo userInfo = SelectUserByUsername(loginRequest.getUsername());

        if(userInfo==null){
            throw new BlogException("用户不存在");
        }

        if (!SecurityUtil.verify(loginRequest.getPassword(),userInfo.getPassword())){
            throw new BlogException("⽤⼾密码不正确");
        }

        LoginResponse response = new LoginResponse();
        response.setUserId(userInfo.getId());

        //字段信息
        Map<String,Object>claims = new HashMap<>();
        claims.put("username",userInfo.getUserName());
        claims.put("userid", String.valueOf(userInfo.getId()));
        String token =  JwtUtils.createToken(claims);

        response.setToken(token);

        return response;
    }

    @Override
    public UserInfo SelectUserByUsername(String userName) {
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<UserInfo>();
        wrapper.eq(UserInfo::getUserName,userName).eq(UserInfo::getDeleteFlag,0);
        return mapper.selectOne(wrapper);
    }

    @Override
    public UserInfoResponse getUserInfoById(Integer id) {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<UserInfo>();
        lambdaQueryWrapper.eq(UserInfo::getId,id).eq(UserInfo::getDeleteFlag,0);

        UserInfo info = mapper.selectOne(lambdaQueryWrapper);

        if(info==null){
            throw new BlogException("博客id不存在",400);
        }

        UserInfoResponse response = info.trans();


        response.setBlogNums(blogService.getBlogNumsById(id));

        return response;
    }

    @Override
    public UserInfoResponse getAuthorInfo(Integer id) {
        BlogInfo blogInfo = blogInfoMapper.selectOne(new LambdaQueryWrapper<BlogInfo>().eq(BlogInfo::getId,id));

        if(blogInfo==null){
            throw new BlogException("博客不存在",400);
        }

        UserInfoResponse userInfoResponse = getUserInfoById(blogInfo.getUserId());

        return userInfoResponse;
    }


}
