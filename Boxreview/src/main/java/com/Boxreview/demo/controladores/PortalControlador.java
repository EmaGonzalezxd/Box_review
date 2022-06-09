package com.Boxreview.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String registro() {
        return "registro.html";
    }

    @GetMapping("/crear")
    public String crear() {
        return "nuevo_usuario.html";
    }

}
