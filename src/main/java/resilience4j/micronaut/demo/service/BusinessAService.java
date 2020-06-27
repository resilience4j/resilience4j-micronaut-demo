package resilience4j.micronaut.demo.service;

import io.github.resilience4j.annotation.Bulkhead;
import io.github.resilience4j.annotation.CircuitBreaker;
import io.github.resilience4j.annotation.Retry;
import io.github.resilience4j.core.SupplierUtils;
import io.micronaut.context.annotation.Executable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientException;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.exceptions.HttpStatusException;
import io.reactivex.Flowable;
import io.reactivex.Single;
import resilience4j.micronaut.demo.exception.BusinessException;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;

@Singleton()
@Named("businessAService")
public class BusinessAService implements Service {
    private static final String BACKEND_A = "backendA";

    @Override
    @CircuitBreaker(name = BACKEND_A)
    @Bulkhead(name = BACKEND_A)
    @Retry(name = BACKEND_A)
    public String failure() {
        throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    @Override
    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public String failureWithFallback() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend A");
    }

    @Override
    public String success() {
        return null;
    }

    @Override
    public String successException() {
        return null;
    }

    @Override
    public String ignoreException() {
        return null;
    }

    @Override
    public Flowable<String> flowableSuccess() {
        return null;
    }

    @Override
    public Flowable<String> flowableFailure() {
        return null;
    }

    @Override
    public Flowable<String> flowableTimeout() {
        return null;
    }

    @Override
    public Single<String> singleSuccess() {
        return null;
    }

    @Override
    public Single<String> singleFailure() {
        return null;
    }

    @Override
    public Single<String> singleTimeout() {
        return null;
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return null;
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        return null;
    }

    @Override
    public CompletableFuture<String> futureTimeout() {
        return null;
    }

    @Executable
    public String fallback() {
        return "Recovered HttpServerErrorException";
    }

}
