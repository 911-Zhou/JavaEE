package zdl.blogsystem.common.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import zdl.blogsystem.common.pojo.Result;
import zdl.blogsystem.common.utils.JwtUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    private void writeJson(HttpServletResponse response, int code, String message) throws IOException {
        response.setStatus(code);
        response.setContentType("application/json;charset=UTF-8");

        Result result = Result.unLogin();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);

        response.getWriter().write(json);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 token
        String token = request.getHeader("Authorization");


        // 验证 token
        if(token==null){
            writeJson(response, 401, "未登录，请先登录");
            return false;
        }

        Claims claims = JwtUtils.parseToken(token);
        if(claims==null){
            writeJson(response, 401, "登录信息已失效，请重新登录");
            return false;
        }

        // 已登录，放行
        return true;
    }
}
