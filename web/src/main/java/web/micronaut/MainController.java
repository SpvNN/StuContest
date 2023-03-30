package web;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.HttpResponse;
import io.micronaut.views.View;

@Controller("/main") 
public class MainController {
    @Get 
    @View("home")
    // @Produces(MediaType.TEXT_PLAIN)
    public HttpResponse index() {
        return HttpResponse.ok(); 
    }

    @Get("/{name}")
    public String hello(String name) {
       return "Hello, " + name;
    }
}
