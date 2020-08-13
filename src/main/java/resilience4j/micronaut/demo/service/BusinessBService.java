package resilience4j.micronaut.demo.service;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.vavr.control.Try;
import resilience4j.micronaut.demo.exception.BusinessException;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Singleton()
@Named("businessBService")
public class BusinessBService implements Service {

    private static final String BACKEND_B = "backendB";

    @Override
    public String failure() {
        throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    @Override
    public String failureWithFallback() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend A");
    }

    @Override
    public String success() {
        return "Hello World from backend A";
    }

    @Override
    public String successException() {
        throw new HttpStatusException(HttpStatus.BAD_REQUEST, "This is a remote client exception");
    }

    @Override
    public String ignoreException() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend A");
    }

    @Override
    public Flowable<String> flowableSuccess() {
        return Flowable.just("Hello", "World");
    }

    @Override
    public Flowable<String> flowableFailure() {
        return Flowable.error(new IOException("BAM!"));
    }

    @Override
    public Flowable<String> flowableTimeout() {
        return Flowable.just("Hello World from backend A").delay(10, TimeUnit.SECONDS);
    }

    @Override
    public Single<String> singleSuccess() {
        return Single.just("Hello World from backend A");
    }

    @Override
    public Single<String> singleFailure() {
        return Single.error(new IOException("BAM!"));
    }

    @Override
    public Single<String> singleTimeout() {
        return Single.just("Hello World from backend A")
            .delay(10, TimeUnit.SECONDS);
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return CompletableFuture.completedFuture("Hello World from backend A");
    }

    @Override
    public CompletableFuture<String> futureFailure() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new IOException("BAM!"));
        return future;
    }

    @Override
    public CompletableFuture<String> futureTimeout() {
        Try.run(() -> Thread.sleep(5000));
        return CompletableFuture.completedFuture("Hello World from backend A");
    }
}
