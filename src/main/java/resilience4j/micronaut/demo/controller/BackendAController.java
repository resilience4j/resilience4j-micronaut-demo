package resilience4j.micronaut.demo.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import resilience4j.micronaut.demo.service.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Supplier;

@Controller("/backendA")
public class BackendAController {

    private final Service businessAService;

    @Inject
    public BackendAController(@Named("businessAService") Service businessAService){
        this.businessAService = businessAService;
    }
//
//    @Get(value = "failure",produces = MediaType.TEXT_PLAIN)
//    public String failure(){
//        return businessAService.failure();
//    }
//
//    @Get(value = "success",produces = MediaType.TEXT_PLAIN)
//    public String success(){
//        return businessAService.success();
//    }
//
//    @Get(value = "ignore", produces = MediaType.TEXT_PLAIN)
//    public String ignore(){
//        return businessAService.ignore();
//    }
//
//    @Get(value = "recover", produces = MediaType.TEXT_PLAIN)
//    public Supplier<String> methodWithRecovery(){
//        return businessAService.methodWithRecovery();
//    }
}
