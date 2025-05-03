package org.zdl.booksystem.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zdl.booksystem.Constants.constants;
import org.zdl.booksystem.model.Result;
import org.zdl.booksystem.model.UserInfo;

/**
 * 登录拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor ⽬标⽅法执⾏前执⾏..");

        //用户未登录判断
        HttpSession session = request.getSession(false);

        if(session==null || session.getAttribute(constants.SESSION_USER_KEY)==null){
            response.setContentType("application/json;charset=UTF-8");
            response.getOutputStream().write(objectMapper.writeValueAsString(Result.unLogin()).getBytes());
            response.setStatus(401);
            return false;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute("session_user_key");

        if (userInfo==null || userInfo.getID()<0 ||
                "".equals(userInfo.getUserName())){
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getOutputStream().write(objectMapper.writeValueAsString(Result.unLogin()).getBytes());
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("LoginInterceptor 目标方法完成后执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("LoginInterceptor 视图渲染完成后执行");
    }
}
