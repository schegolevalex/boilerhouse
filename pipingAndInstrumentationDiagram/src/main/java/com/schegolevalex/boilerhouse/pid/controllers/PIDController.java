package com.schegolevalex.boilerhouse.pid.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pid")
public class PIDController {

    @PostMapping("/")
    public void createNewProject(String name) {

    }
}
