package com.Boxreview.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
//    @GetMapping("/")
//    public String registro() {
//        return "registro.html";
//    }
    
    @PostMapping("/")
    public String registrar() {
        return "registro.html";
    }
}