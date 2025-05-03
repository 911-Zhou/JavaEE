package zdl.blogsystem.common.advice;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.context.MessageSourceResolvable;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import zdl.blogsystem.common.excepiton.BlogException;
import zdl.blogsystem.common.pojo.Result;


@ResponseBody
@ControllerAdvice
@Slf4j
public class ExcepitonAdvice {
    //统一异常处理
    @ExceptionHandler(Exception.class)
    public Result handler(Exception e){
        log.error("发⽣异常,", e);
        return Result.serverError("服务器发生异常");
    }

    @ExceptionHandler(BlogException.class)
    public Result blogExceptionHandler(BlogException e){
        log.error("发生运行时异常,",e);
        return Result.fail(e.getMessage(),e.getCode());
    }


    // 用于 @RequestBody 对象的校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        log.error("参数校验失败",e);
        String message = e.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("参数校验失败");
        return Result.validateFailed(message);
    }

    // 用于普通参数、方法级别参数校验失败
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Result handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        log.error("参数校验失败",e);
        String message = e.getParameterValidationResults().stream()
                .flatMap(validationResult -> validationResult.getResolvableErrors().stream())
                .findFirst()
                .map(MessageSourceResolvable::getDefaultMessage)
                .orElse("参数校验失败");

        return Result.validateFailed(message);
    }

    //资源路径错误
    @ExceptionHandler(NoResourceFoundException.class)
    public Result noResourceFoundHandle(Exception e){
        log.error("路径错误,",e);
        return Result.noResourceFound(e.getMessage());
    }

    //令牌过期
    @ExceptionHandler(ExpiredJwtException.class)
    public Result expiredJwtHandle(Exception e){
        log.error("令牌过期",e);
        return Result.unLogin();
    }

    //token签名错误
    @ExceptionHandler(SignatureException.class)
    public Result signatureHandle(Exception e){
        return Result.unLogin();
    }
}
