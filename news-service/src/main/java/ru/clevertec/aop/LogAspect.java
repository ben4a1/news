package ru.clevertec.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Class with advices for logging
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * advice (logging) when working with Controllers
     *
     * @param joinPoint              ProceedingJoinPoint exposes the proceed(..) method in order to support around advice in @AJ aspects
     * @param controller             class which works with request
     * @param requestClassAnnotation annotation @RequestMapping
     * @return the result of joinPoint proceeding
     * @throws Throwable if the invoked proceed throws anything
     */
    @Around(value = "ru.clevertec.aop.LayerPointcuts.isControllerLayer()" +
                    "&& target(controller)" +
                    "&& @within(requestClassAnnotation)", argNames = "joinPoint,controller,requestClassAnnotation")
    public Object addControllerLog(ProceedingJoinPoint joinPoint,
                                   Object controller,
                                   RequestMapping requestClassAnnotation) throws Throwable {
        String requestType = getRequestType(joinPoint);
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Starting {} in {}.{}() with args: {}", requestType, controller, methodName, args);
        Object result = joinPoint.proceed();
        log.info("Sending response with {}", result);
        return result;
    }

    /**
     * advice (logging) when working with Services
     *
     * @param joinPoint ProceedingJoinPoint exposes the proceed(..) method in order to support around advice in @AJ aspects
     * @param service   service that accepts a request from a controller
     * @return the result of joinPoint proceeding
     * @throws Throwable if the invoked proceed throws anything
     */
    @Around("ru.clevertec.aop.LayerPointcuts.isServiceLayer()" +
            "&& target(service)")
    public Object addServiceLog(ProceedingJoinPoint joinPoint, Object service) throws Throwable {
        Object[] args = joinPoint.getArgs();
        log.info("Invoke {} method with args: {}", service, args);
        Object result = joinPoint.proceed();
        log.info("{} returns {}", service, result);
        return result;
    }

    /**
     * advice (logging) when working with Repositories
     *
     * @param joinPoint  ProceedingJoinPoint exposes the proceed(..) method in order to support around advice in @AJ aspects
     * @param repository repository that accepts a request from a service
     * @return the result of joinPoint proceeding
     * @throws Throwable if the invoked proceed throws anything
     */
    @Around("ru.clevertec.aop.LayerPointcuts.isRepositoryLayer()" +
            "&& target(repository)")
    public Object addRepositoryLog(ProceedingJoinPoint joinPoint, Object repository) throws Throwable {
        Object[] args = joinPoint.getArgs();
        log.info("Invoke {} method with args: {}", repository, args);
        Object result = joinPoint.proceed();
        log.info("{} returns {}", repository, result);
        return result;
    }

    /**
     * method to determine the request type
     *
     * @param joinPoint  ProceedingJoinPoint exposes the proceed(..) method in order to support around advice in @AJ aspects
     * @return String value (GET, POST, etc.)
     */
    private String getRequestType(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestMethod[] methods = Arrays.stream(method.getAnnotations())
                .map(Annotation::annotationType)
                .filter(annotation -> annotation.isAnnotationPresent(RequestMapping.class))
                .map(annotation -> annotation.getAnnotation(RequestMapping.class).method())
                .findFirst()
                .orElse(null);
        return Arrays.toString(methods);
    }
}
