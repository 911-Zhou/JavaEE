package zdl.blogsystem.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdl.blogsystem.common.pojo.request.LoginRequest;
import zdl.blogsystem.common.pojo.response.LoginResponse;
import zdl.blogsystem.common.pojo.response.UserInfoResponse;
import zdl.blogsystem.service.impl.UserServiceImpl;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    UserServiceImpl userService;

    //登录
    @RequestMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        log.info("user: loginRequest:{}",loginRequest);
        return userService.login(loginRequest);
    }

    @RequestMapping("/getUserInfo")
    public UserInfoResponse getUserInfo(Integer userId){
        return userService.getUserInfoById(userId);
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfoResponse getAuthorInfo(Integer blogId){
        return userService.getAuthorInfo(blogId);
    }
}
