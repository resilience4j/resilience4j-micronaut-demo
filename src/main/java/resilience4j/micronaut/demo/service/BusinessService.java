package resilience4j.micronaut.demo.service;

import java.util.function.Supplier;

public interface BusinessService {
    String failure();

    String success();

    String ignore();

    Supplier<String> methodWithRecovery();
}
