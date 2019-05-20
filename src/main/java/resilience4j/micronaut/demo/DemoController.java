package resilience4j.micronaut.demo;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller("/hello")
public class DemoController {

    @Inject
    private DemoService demoService;

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return demoService.sayHelloWorld();
    }
}
