package org.zdl.booksystem.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zdl.booksystem.Constants.constants;
import org.zdl.booksystem.mapper.UserMapper;
import org.zdl.booksystem.model.UserInfo;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public Boolean checkUserLogin(String username, String password, HttpSession session, HttpServletResponse httpServletResponse){
        //账号密码为空
        if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            return false;
        }

        if(session==null){
            log.warn("checkUserLogin，用户session为空");
        }

        UserInfo userInfo = userMapper.SelectUser(username);

        //账号找不到
        if(userInfo == null){
            return false;
        }
        //账号密码正确
        else if(username.equals(userInfo.getUserName()) && password.equals(userInfo.getPassword())){
//            Cookie cookie = new Cookie("username",username);
//            cookie.setMaxAge(60*60*24);
//            cookie.setPath("/");  // 默认是当前路径，这里设置为根路径
//            httpServletResponse.addCookie(cookie);

            userInfo.setPassword("");
            session.setAttribute(constants.SESSION_USER_KEY,userInfo);
            return true;
        }

        //密码不正确
        return false;
    }
}

