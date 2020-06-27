package resilience4j.micronaut.demo.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import resilience4j.micronaut.demo.service.BusinessService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Supplier;

@Controller("/backendA")
public class BackendAController {

    private final BusinessService businessAService;

    @Inject
    public BackendAController(@Named("businessAService") BusinessService businessAService){
        this.businessAService = businessAService;
    }

    @Get(value = "failure")
    public String failure(){
        return businessAService.failure();
    }

    @Get(value = "success")
    public String success(){
        return businessAService.success();
    }

    @Get(value = "ignore", produces = MediaType.TEXT_PLAIN)
    public String ignore(){
        return businessAService.ignore();
    }

    @Get(value = "recover", produces = MediaType.TEXT_PLAIN)
    public Supplier<String> methodWithRecovery(){
        return businessAService.methodWithRecovery();
    }
}
