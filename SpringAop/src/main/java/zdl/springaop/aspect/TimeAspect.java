package zdl.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* zdl.springaop.controller.*.*(..))")
    public Object RecodeTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //记录⽅法执⾏开始时间
        long begin = System.currentTimeMillis();
        //执⾏原始⽅法
        Object result = proceedingJoinPoint.proceed();
        //记录⽅法执⾏结束时间
        long end = System.currentTimeMillis();
        //记录⽅法执⾏耗时
        log.info(proceedingJoinPoint.getSignature() + "执⾏耗时: {}ms", end - begin);
        return result;
    }
}
