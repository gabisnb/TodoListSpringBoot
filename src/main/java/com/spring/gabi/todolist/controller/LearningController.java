package com.spring.gabi.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/learningController")
public class LearningController {

    //request do tipo get, seu endereço é "localhost:8000/learningController/firstMessage"
    @GetMapping("/firstMessage")
    public String firstMessage(){
        return "Funcionando";
    }
}
