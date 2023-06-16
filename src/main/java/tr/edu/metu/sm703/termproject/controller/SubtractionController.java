package tr.edu.metu.sm703.termproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tr.edu.metu.sm703.termproject.service.SubtractionService;

@RestController
public class SubtractionController {

    private final SubtractionService subtractionService;

    @Autowired
    public SubtractionController(SubtractionService subtractionService) {
        this.subtractionService = subtractionService;
    }

    @GetMapping("/subtract")
    public int add(@RequestParam int a, @RequestParam int b) {
        return subtractionService.subtract(a, b);
    }
}

