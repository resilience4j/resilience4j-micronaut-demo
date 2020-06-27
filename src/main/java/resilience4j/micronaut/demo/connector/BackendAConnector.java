package resilience4j.micronaut.demo.connector;


import io.github.resilience4j.annotation.CircuitBreaker;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.reactivex.Observable;
import resilience4j.micronaut.demo.exception.BusinessException;

import javax.inject.Named;

/**
 * This Connector shows how to use the CircuitBreaker annotation.
 */
@CircuitBreaker(name = "backendA")
@Named("backendA")
public class BackendAConnector implements Connector {

    @Override
    public String failure() {
        throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    @Override
    public String ignoreException() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend A");
    }

    @Override
    public String success() {
        return "Hello World from backend A";
    }

    @Override
    public Observable<String> methodWhichReturnsAStream() {
        return Observable.never();
    }
}