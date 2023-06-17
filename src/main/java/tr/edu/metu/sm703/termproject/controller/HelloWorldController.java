package tr.edu.metu.sm703.termproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${WHICH_ENV:hello1}")
    private String environment;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hi World from: " + environment +".";
    }
}

