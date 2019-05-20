package resilience4j.micronaut.demo.autoconfigure;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.micronaut.context.annotation.ConfigurationBuilder;
import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;

@EachProperty(value = "resilience4j.circuitbreaker")
public class CircuitBreakerConfiguration {

    private final String name;

    @SuppressWarnings("WeakerAccess")
    @ConfigurationBuilder(allowZeroArgs = true, prefixes = { "" })
    CircuitBreakerConfig.Builder builder = CircuitBreakerConfig.custom();

    public CircuitBreakerConfiguration(@Parameter String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * The configuration builder.
     *
     * @return The builder
     */
    public CircuitBreakerConfig.Builder getBuilder() {
        return builder;
    }

}
