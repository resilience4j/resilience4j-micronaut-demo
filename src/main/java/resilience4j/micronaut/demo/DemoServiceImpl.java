package resilience4j.micronaut.demo;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import resilience4j.micronaut.demo.autoconfigure.CircuitBreakerConfiguration;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DemoServiceImpl implements DemoService {

    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final CircuitBreaker circuitBreaker;
    private CircuitBreakerConfiguration configuration;

    @Inject
    public DemoServiceImpl(CircuitBreakerRegistry circuitBreakerRegistry, CircuitBreakerConfiguration configuration){
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");
        this.configuration = configuration;
    }

    @Override
    public String sayHelloWorld() {
        return circuitBreaker.executeSupplier(() -> "Hello world");
    }
}
