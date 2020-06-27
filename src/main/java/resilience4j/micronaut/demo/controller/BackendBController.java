package resilience4j.micronaut.demo.controller;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import resilience4j.micronaut.demo.service.BusinessService;

import javax.inject.Inject;
import javax.inject.Named;

@Controller("/hello")
public class BackendBController {

    private final BusinessService businessBService;

    @Inject
    public BackendBController(@Named("businessBService") BusinessService businessBService){
        this.businessBService = businessBService;
    }

    @Get(value = "failure")
    public String backendBFailure(){
        return businessBService.failure();
    }

    @Get(value = "success")
    public String backendBSuccess(){
        return businessBService.success();
    }

    @Get(value = "ignore")
    public String ignore(){
        return businessBService.ignore();
    }
}
