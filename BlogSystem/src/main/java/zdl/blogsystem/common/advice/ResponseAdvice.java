package zdl.blogsystem.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import zdl.blogsystem.common.pojo.Result;
import org.springframework.http.server.ServletServerHttpResponse;

/**
 * 统一结果返回
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    private ObjectMapper mapper = new ObjectMapper();

    // 判断是否需要对响应体进行处理
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    //辅助方法：设置 HTTP 状态码
    private void setHttpStatusCode(ServerHttpResponse response, Integer code) {
        if (response instanceof ServletServerHttpResponse servletResponse) {
            // 判断 code 是否为标准 HTTP 状态码（100～599），否则忽略
            if (code != null && code >= 100 && code <= 599) {
                servletResponse.getServletResponse().setStatus(code);
            }
        }
    }


    // 对响应体进行处理
    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 判断是否已经是 Result 类型，避免重复封装
        if (body instanceof Result) {
            Result result = (Result)body;
            setHttpStatusCode(response, result.getCode());
            return body; // 如果已经是 Result 类型，直接返回
        }

        //对String类型特殊处理
        if (body instanceof String){
            return mapper.writeValueAsString(Result.success(body));
        }

        // 其他对象，封装并设置状态码
        Result result = Result.success(body);
        setHttpStatusCode(response, result.getCode());
        return result;
    }

}
