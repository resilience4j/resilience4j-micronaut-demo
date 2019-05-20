package resilience4j.micronaut.demo.autoconfigure;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Inject;

@Factory
public class CircuitBreakerFactory {

    @Inject
    private BeanContext beanContext;

    @Inject
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @EachBean(CircuitBreakerConfiguration.class)
    public CircuitBreaker circuitBreaker(CircuitBreakerConfiguration circuitBreakerConfiguration) {
        CircuitBreakerConfig config = circuitBreakerConfiguration.getBuilder().build();
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(circuitBreakerConfiguration.getName(), config);
        registerHealthIndicator(circuitBreaker);
        return circuitBreaker;
    }

    private void registerHealthIndicator(CircuitBreaker circuitBreaker) {
        beanContext.registerSingleton(new CircuitBreakerHealthIndicator(circuitBreaker));
    }
}
