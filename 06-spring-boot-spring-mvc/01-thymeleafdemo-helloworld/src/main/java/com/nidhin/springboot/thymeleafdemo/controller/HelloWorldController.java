package com.nidhin.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    // Need a new controller method to show the initial HTML form

    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    //need a controller method to process the form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    //need a controller method to read form data
    //add data to model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){

        //read the request parameter from the HTML form
        String name = request.getParameter("studentName");
        //convert data to all caps
        name = name.toUpperCase();

        //create the message
        String result = "Yo! " + name;

        //add message to the model
        model.addAttribute("message", result);

        //send back the result to the html form; back to user
        return "helloworld";
    }

    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String name,
                                          Model model){

        //convert data to all caps
        name = name.toUpperCase();

        //create the message
        String result = "Hey My frined! " + name;

        //add message to the model
        model.addAttribute("message", result);

        //send back the result to the html form; back to user
        return "helloworld";
    }
}
