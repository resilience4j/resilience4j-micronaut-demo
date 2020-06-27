package resilience4j.micronaut.demo.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import io.reactivex.Single;
import resilience4j.micronaut.demo.service.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.CompletableFuture;

@Controller("/backendA")
public class BackendAController {

    private final Service businessAService;

    @Inject
    public BackendAController(@Named("businessAService") Service businessAService) {
        this.businessAService = businessAService;
    }

    @Get(value = "failure", produces = MediaType.TEXT_PLAIN)
    public String failure() {
        return businessAService.failure();
    }

    @Get(value = "success", produces = MediaType.TEXT_PLAIN)
    public String success() {
        return businessAService.success();
    }

    @Get(value = "successException", produces = MediaType.TEXT_PLAIN)
    public String successException() {
        return businessAService.successException();
    }

    @Get(value = "ignore", produces = MediaType.TEXT_PLAIN)
    public String ignore() {
        return businessAService.ignoreException();
    }

    @Get(value = "monoSuccess", produces = MediaType.TEXT_PLAIN)
    public Single<String> monoSuccess() {
        return businessAService.monoSuccess();
    }

    @Get(value = "monoFailure", produces = MediaType.TEXT_PLAIN)
    public Single<String> monoFailure() {
        return businessAService.monoFailure();
    }

    @Get(value = "fluxSuccess", produces = MediaType.TEXT_PLAIN)
    public Flowable<String> fluxSuccess() {
        return businessAService.fluxSuccess();
    }

    @Get(value = "monoTimeout", produces = MediaType.TEXT_PLAIN)
    public Single<String> monoTimeout() {
        return businessAService.monoTimeout();
    }

    @Get(value = "fluxTimeout", produces = MediaType.TEXT_PLAIN)
    public Flowable<String> fluxTimeout() {
        return businessAService.fluxTimeout();
    }

    @Get(value = "futureFailure", produces = MediaType.TEXT_PLAIN)
    public CompletableFuture<String> futureFailure() {
        return businessAService.futureFailure();
    }

    @Get(value = "futureSuccess", produces = MediaType.TEXT_PLAIN)
    public CompletableFuture<String> futureSuccess() {
        return businessAService.futureSuccess();
    }

    @Get(value = "futureTimeout", produces = MediaType.TEXT_PLAIN)
    public CompletableFuture<String> futureTimeout() {
        return businessAService.futureTimeout();
    }

    @Get(value = "fluxFailure", produces = MediaType.TEXT_PLAIN)
    public Flowable<String> fluxFailure() {
        return businessAService.fluxFailure();
    }

    @Get(value = "fallback", produces = MediaType.TEXT_PLAIN)
    public String failureWithFallback() {
        return businessAService.failureWithFallback();
    }

}
