package resilience4j.micronaut.demo.service;

import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;

@Singleton()
@Named("businessBService")
public class BusinessBService implements Service {

    @Override
    public String failure() {
        return null;
    }

    @Override
    public String failureWithFallback() {
        return null;
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
}
