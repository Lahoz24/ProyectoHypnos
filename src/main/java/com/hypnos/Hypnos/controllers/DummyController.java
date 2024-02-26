package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.auth.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//metodo que devuelda response pero que lo que haya dentro sea string(Hola Mundo)
@RestController
@RequestMapping("/api/dummies")
@Slf4j
public class DummyController {
    @GetMapping("/hola")
    public ResponseEntity<String> HolaMundo(){
        String respuesta = "Hola Mundo";
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/adios")
    public ResponseEntity<String> AdiosMundo(){
        String respuesta = "Adios Mundo";
        return ResponseEntity.ok(respuesta);
    }
}
