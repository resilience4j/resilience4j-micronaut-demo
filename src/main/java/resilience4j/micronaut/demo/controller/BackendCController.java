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

@Controller("/backendC")
public class BackendCController {

    private final Service businessBService;

    @Inject
    public BackendCController(@Named("businessCService") Service businessBService) {
        this.businessBService = businessBService;
    }

    @Get(value = "failure", produces = MediaType.TEXT_PLAIN)
    public String failure() {
        return businessBService.failure();
    }

    @Get(value = "success", produces = MediaType.TEXT_PLAIN)
    public String success() {
        return businessBService.success();
    }

    @Get(value = "successException", produces = MediaType.TEXT_PLAIN)
    public String successException() {
        return businessBService.successException();
    }

    @Get(value = "ignore", produces = MediaType.TEXT_PLAIN)
    public String ignore() {
        return businessBService.ignoreException();
    }

    @Get(value = "monoSuccess", produces = MediaType.TEXT_PLAIN)
    public Single<String> monoSuccess() {
        return businessBService.monoSuccess();
    }

    @Get(value = "monoFailure", produces = MediaType.TEXT_PLAIN)
    public Single<String> monoFailure() {
        return businessBService.monoFailure();
    }

    @Get(value = "fluxSuccess", produces = MediaType.TEXT_PLAIN)
    public Flowable<String> fluxSuccess() {
        return businessBService.fluxSuccess();
    }

    @Get(value = "monoTimeout", produces = MediaType.TEXT_PLAIN)
    public Single<String> monoTimeout() {
        return businessBService.monoTimeout();
    }

    @Get(value = "fluxTimeout", produces = MediaType.TEXT_PLAIN)
    public Flowable<String> fluxTimeout() {
        return businessBService.fluxTimeout();
    }

    @Get(value = "futureFailure", produces = MediaType.TEXT_PLAIN)
    public CompletableFuture<String> futureFailure() {
        return businessBService.futureFailure();
    }

    @Get(value = "futureSuccess", produces = MediaType.TEXT_PLAIN)
    public CompletableFuture<String> futureSuccess() {
        return businessBService.futureSuccess();
    }

    @Get(value = "futureTimeout", produces = MediaType.TEXT_PLAIN)
    public CompletableFuture<String> futureTimeout() {
        return businessBService.futureTimeout();
    }

    @Get(value = "fluxFailure", produces = MediaType.TEXT_PLAIN)
    public Flowable<String> fluxFailure() {
        return businessBService.fluxFailure();
    }

    @Get(value = "fallback", produces = MediaType.TEXT_PLAIN)
    public String failureWithFallback() {
        return businessBService.failureWithFallback();
    }
}
