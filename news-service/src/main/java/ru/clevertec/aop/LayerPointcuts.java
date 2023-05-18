package ru.clevertec.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Class with pointcuts for database/service/controller
 */
@Aspect
@Component
public class LayerPointcuts {


    /**
     * Check class for existing annotation
     * {@link org.springframework.web.bind.annotation.RestController}
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void isControllerLayer() {
    }

    /**
     * check class for existing annotation
     * {@link org.springframework.stereotype.Service}
     */
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer() {
    }

    /**
     * check proxy object for existing annotation
     * {@link org.springframework.data.repository.Repository}
     */
    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }
}
