package resilience4j.micronaut.demo.autoconfigure;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent;
import io.github.resilience4j.consumer.DefaultEventConsumerRegistry;
import io.github.resilience4j.consumer.EventConsumerRegistry;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class CircuitBreakerRegistryFactory {

    @Singleton
    public CircuitBreakerRegistry circuitBreakerRegistry(EventConsumerRegistry<CircuitBreakerEvent> eventConsumerRegistry) {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        registerEventConsumer(circuitBreakerRegistry, eventConsumerRegistry);
        return circuitBreakerRegistry;
    }

    @Singleton
    public EventConsumerRegistry<CircuitBreakerEvent> eventConsumerRegistry() {
        return new DefaultEventConsumerRegistry<>();
    }

    /**
     * Registers the post creation consumer function that registers the consumer events to the circuit breakers.
     *
     * @param circuitBreakerRegistry The circuit breaker registry.
     * @param eventConsumerRegistry  The event consumer registry.
     */
    private void registerEventConsumer(CircuitBreakerRegistry circuitBreakerRegistry,
                                       EventConsumerRegistry<CircuitBreakerEvent> eventConsumerRegistry) {
        circuitBreakerRegistry.getEventPublisher().onEntryAdded(event -> registerEventConsumer(eventConsumerRegistry, event.getAddedEntry()));
    }

    private void registerEventConsumer(EventConsumerRegistry<CircuitBreakerEvent> eventConsumerRegistry, CircuitBreaker circuitBreaker) {
        circuitBreaker.getEventPublisher().onEvent(eventConsumerRegistry.createEventConsumer(circuitBreaker.getName(), 100));
    }
}
