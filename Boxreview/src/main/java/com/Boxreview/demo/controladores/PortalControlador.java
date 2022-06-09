package com.Boxreview.demo.controladores;

import com.Boxreview.demo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia) {

        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Email: " + email);
        System.out.println("contra: " + contrasenia);
        
        return "index.html";
    }
}
