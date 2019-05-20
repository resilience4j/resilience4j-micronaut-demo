package resilience4j.micronaut.demo.autoconfigure;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.AbstractHealthIndicator;

import java.util.HashMap;
import java.util.Map;

/**
 * A Spring Boot health indicators which adds the state of a CircuitBreaker and it's metrics to the health endpoints
 */
public class CircuitBreakerHealthIndicator extends AbstractHealthIndicator {

    private static final String FAILURE_RATE = "failureRate";
    private static final String FAILURE_RATE_THRESHOLD = "failureRateThreshold";
    private static final String BUFFERED_CALLS = "bufferedCalls";
    private static final String FAILED_CALLS = "failedCalls";
    private static final String NOT_PERMITTED = "notPermittedCalls";
    private static final String MAX_BUFFERED_CALLS = "maxBufferedCalls";
    private static final String STATE = "state";
    private final CircuitBreaker circuitBreaker;

    public CircuitBreakerHealthIndicator(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    private HealthStatus getHealthStatus() {
        switch (circuitBreaker.getState()) {
            case CLOSED:
                return HealthStatus.UP;
            case OPEN:
                return HealthStatus.DOWN;
            default:
                return HealthStatus.UNKNOWN;
        }
    }

    @Override
    protected Object getHealthInformation() {
        this.healthStatus = getHealthStatus();
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        CircuitBreakerConfig config = circuitBreaker.getCircuitBreakerConfig();
        Map<String, Object> healthInformation = new HashMap<>();
        healthInformation.put(FAILURE_RATE, metrics.getFailureRate() + "%");
        healthInformation.put(FAILURE_RATE_THRESHOLD, config.getFailureRateThreshold() + "%");
        healthInformation.put(MAX_BUFFERED_CALLS, metrics.getMaxNumberOfBufferedCalls());
        healthInformation.put(BUFFERED_CALLS, metrics.getNumberOfBufferedCalls());
        healthInformation.put(FAILED_CALLS, metrics.getNumberOfFailedCalls());
        healthInformation.put(NOT_PERMITTED, metrics.getNumberOfNotPermittedCalls());
        healthInformation.put(STATE, circuitBreaker.getState());
        return healthInformation;
    }

    @Override
    protected String getName() {
        return circuitBreaker.getName();
    }
}
