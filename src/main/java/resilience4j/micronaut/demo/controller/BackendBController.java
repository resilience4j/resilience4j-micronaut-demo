package resilience4j.micronaut.demo.controller;


import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import resilience4j.micronaut.demo.service.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Controller("/hello")
public class BackendBController {

    private final Service businessBService;

    @Inject
    public BackendBController(@Named("businessBService") Service businessBService){
        this.businessBService = businessBService;
    }
//
//    @Get(value = "failure",produces = MediaType.TEXT_PLAIN)
//    public String backendBFailure(){
//        return businessBService.failure();
//    }
//
//    @Get(value = "success",produces = MediaType.TEXT_PLAIN)
//    public String backendBSuccess(){
//        return businessBService.success();
//    }
//
//    @Get(value = "ignore",produces = MediaType.TEXT_PLAIN)
//    public String ignore(){
//        return businessBService.ignore();
//    }
}
