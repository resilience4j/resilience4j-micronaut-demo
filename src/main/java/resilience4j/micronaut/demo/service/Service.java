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

    Flowable<String> fluxSuccess();

    Flowable<String> fluxFailure();

    Flowable<String> fluxTimeout();

    Single<String> monoSuccess();

    Single<String> monoFailure();

    Single<String> monoTimeout();

    CompletableFuture<String> futureSuccess();

    CompletableFuture<String> futureFailure();

    CompletableFuture<String> futureTimeout();
}
