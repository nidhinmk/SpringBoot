package com.nidhin.springboot.springapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    //Expose '/' that return "Hello World"
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    //Expose a new endpoint for workout
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a solid 10K!";
    }

    //Expose a new endpoint for fortune
    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "You are born with luck!";
    }

}
