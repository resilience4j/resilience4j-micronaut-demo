package resilience4j.micronaut.demo.service;

import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.concurrent.CompletableFuture;

public interface Service {

    String failure();

    String failureWithFallback();

    String success();

    String successException();

    String ignoreException();

    Flowable<String> flowableSuccess();

    Flowable<String> flowableFailure();

    Flowable<String> flowableTimeout();

    Single<String> singleSuccess();

    Single<String> singleFailure();

    Single<String> singleTimeout();

    CompletableFuture<String> futureSuccess();

    CompletableFuture<String> futureFailure();

    CompletableFuture<String> futureTimeout();
}
