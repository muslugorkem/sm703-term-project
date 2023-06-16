package tr.edu.metu.sm703.termproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}

