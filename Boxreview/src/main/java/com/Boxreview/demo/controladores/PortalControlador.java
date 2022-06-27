package com.Boxreview.demo.controladores;

import com.Boxreview.demo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String inicio() {
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam MultipartFile foto, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia) {

        try {

            usuarioServicio.crear(foto, nombre, apellido, email, contrasenia);
            modelo.put("titulo", "Felicidades!");
            modelo.put("descripcion", "Usuario registrado satisfactoriamente.");

            return "index.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            modelo.put("error", ex.getMessage());
            return "index.html";
        }

    }
    
}
