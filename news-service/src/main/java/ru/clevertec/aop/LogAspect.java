package ru.clevertec.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("ru.clevertec.aop.LayerPointcuts.isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    @Pointcut("execution(public * ru.clevertec.service.impl.*.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod()" +
                    "&& args(id)" +
                    "&& target(service)", argNames = "id,service")
    public void addLogging(Object id, Object service) {
        log.info("invoked findById method in class {} with id {}", service, id);
    }
}