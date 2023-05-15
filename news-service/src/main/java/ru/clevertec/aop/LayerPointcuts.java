package ru.clevertec.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LayerPointcuts {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void isControllerLayer() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer() {
    }

    @Pointcut("target(ru.clevertec.repository.NewsRepository)")
    public void isRepositoryLayer() {
    }
}
