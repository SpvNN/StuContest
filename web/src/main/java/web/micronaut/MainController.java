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

    //***
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
 
        return "login";
    }
 
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
 
        if (bindingResult.hasErrors()) {
            return "/login";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/login";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "forward:/login";
        }
 
        return "/login";
    }
    //***
}
