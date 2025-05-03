package zdl.blogsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zdl.blogsystem.Mapper.BlogInfoMapper;
import zdl.blogsystem.common.excepiton.BlogException;
import zdl.blogsystem.common.pojo.BlogInfo;
import zdl.blogsystem.common.pojo.request.BlogAddRequest;
import zdl.blogsystem.common.pojo.request.BlogUpdateRequest;
import zdl.blogsystem.common.pojo.response.BlogInfoResponse;
import zdl.blogsystem.service.BlogService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Resource(name = "blogInfoMapper")
    BlogInfoMapper mapper;

    @Override
    public List<BlogInfoResponse> getList() {
        List<BlogInfo> blogInfos;
        LambdaQueryWrapper<BlogInfo> lambdaQueryWrapper = new LambdaQueryWrapper<BlogInfo>().eq(BlogInfo::getDeleteFlag,0).
                orderByAsc(BlogInfo::getId);
        blogInfos = mapper.selectList(lambdaQueryWrapper);

        List<BlogInfoResponse> responses = blogInfos.stream().map(info->{
//            BlogInfoResponse blogInfoResponse = new BlogInfoResponse();
//            BeanUtils.copyProperties(info,blogInfoResponse);
//            return blogInfoResponse;
            return info.trans();
        }).collect(Collectors.toList());

        return responses;
    }

    @Override
    public BlogInfoResponse getBlogDetail(int blogId) {
        BlogInfo blogInfo = getBlogInfo(blogId);

//        BlogInfoResponse response = new BlogInfoResponse();
//        BeanUtils.copyProperties(blogInfo,response);

        return blogInfo.trans();
    }

    //获取指定id博客内容
    @Override
    public BlogInfo getBlogInfo(int blogId) {
        return mapper.selectOne(new LambdaQueryWrapper<BlogInfo>().eq(BlogInfo::getId,blogId));
    }

    @Override
    public boolean addBlog(BlogAddRequest blogAddRequest) {
        int res = 0;

        try {
            res = mapper.insert(blogAddRequest.trans());

            if(res==1){
                //成功
                return true;
            }

            //插入失败
            return false;
        } catch (Exception e) {
            log.error("发布博客失败",e);
            throw new BlogException("上传博客失败",500);
        }
    }

    @Override
    public Integer getBlogNumsById(Integer userId) {
        Integer res = mapper.selectList(new LambdaQueryWrapper<BlogInfo>().eq(BlogInfo::getUserId,userId)
                .eq(BlogInfo::getDeleteFlag,0)).size();
        log.info("userId:"+userId+",博客数量为:" + res);
        return res;
    }

    @Override
    public boolean deleteBlog(Integer blogId) {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(1);


        try {
            int res =  mapper.updateById(blogInfo);

            if(res==0){
                return false;
            }
        } catch (Exception e) {
            throw new BlogException("删除失败",400);
        }

        return true;
    }

    @Override
    public boolean updateBlog(BlogUpdateRequest request) {

        try {
            int res = mapper.updateById(request.trans());
            if(res==0)return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
