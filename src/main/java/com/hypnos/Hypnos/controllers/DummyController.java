package com.hypnos.Hypnos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dummies")
@Slf4j
public class DummyController {

    @Autowired
    public DummyController(){
    }
    @GetMapping("")
    public
}
