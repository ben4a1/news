package ru.clevertec.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.service.impl.CommentService;
import ru.clevertec.service.impl.NewsService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("ru.clevertec.aop.LayerPointcuts.isControllerLayer()" +
            "&& @annotation(restLogAnnotation)" +
            "&& target(controller)")
    public Object addLoggingForController(ProceedingJoinPoint joinPoint, RestLog restLogAnnotation, Object controller) throws Throwable {
        String uri = restLogAnnotation.uri();
        String requestType = getRequestType(joinPoint);
        log.info("Starting {} in {}.{}", requestType, controller, uri);
        Object result = joinPoint.proceed();
        log.info("Sending response with {}", result);
        return result;
    }

    @Before("ru.clevertec.aop.LayerPointcuts.isServiceLayer()" +
            "&& target(service)")
    public void addServiceLog(Object service) {
        log.info("Invoke {} method", service);
    }

    @Before("ru.clevertec.aop.LayerPointcuts.isRepositoryLayer()" +
            "&& target(repository)")
    public void addRepositoryLog(Object repository) {
        log.info("Invoke {} method", repository);
    }

    private String getRequestType(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return Arrays.toString(Objects.requireNonNull(Arrays.stream(method.getAnnotations())
                .map(Annotation::annotationType)
                .filter(annotation -> annotation.isAnnotationPresent(RequestMapping.class))
                .map(annotation -> annotation.getAnnotation(RequestMapping.class).method())
                .findFirst()
                .orElse(null)));
    }
}