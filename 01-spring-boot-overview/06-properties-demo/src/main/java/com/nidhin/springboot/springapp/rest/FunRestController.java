package com.nidhin.springboot.springapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    //inject properties for coach.name and team.name

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    //Expose new endpoint for "Team Info"
    @GetMapping("/teaminfo")
    public String getTeamInfo(){
        return "Coach name : "+coachName+ " Team name: "+teamName;
    }

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
