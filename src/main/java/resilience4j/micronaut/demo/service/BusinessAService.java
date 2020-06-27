package resilience4j.micronaut.demo.service;

import io.github.resilience4j.core.SupplierUtils;
import resilience4j.micronaut.demo.connector.Connector;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.function.Supplier;

@Singleton()
@Named("businessBService")
public class BusinessAService implements BusinessService {

    private final Connector backendAConnector;

    public BusinessAService(@Named("backendAConnector") Connector backendAConnector) {
        this.backendAConnector = backendAConnector;
    }

    @Override
    public String failure() {
        return backendAConnector.failure();
    }

    @Override
    public String success() {
        return backendAConnector.success();
    }

    @Override
    public String ignore() {
        return backendAConnector.ignoreException();
    }

    @Override
    public Supplier<String> methodWithRecovery() {
        return SupplierUtils.recover(backendAConnector::failure, (th) -> recovery());
    }

    private String recovery() {
        return "Hello world from recovery";
    }
}
