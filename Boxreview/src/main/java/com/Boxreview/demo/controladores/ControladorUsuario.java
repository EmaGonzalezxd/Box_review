package com.Boxreview.demo.controladores;

import com.Boxreview.demo.ErrorServicio.ErrorServicio;
import com.Boxreview.demo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registro")
public class ControladorUsuario {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia) {

        try {
            usuarioServicio.crear(nombre, apellido, email, contrasenia);
            modelo.put("titulo", "Felicidades!");
            modelo.put("descripcion", "Usuario registrado satisfactoriamente.");
            return "index.html";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "/registro.html";
        }

    }
    
    @PostMapping("/logear")
    public String logear(ModelMap modelo, @RequestParam String nombre, @RequestParam String email, @RequestParam String contrasenia) {

    
        try {
            usuarioServicio.iniciarSesion(nombre, contrasenia);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            return "login.html";
        }
        modelo.put("titulo", "Felicidades!");
        modelo.put("descripcion", "Usuario registrado satisfactoriamente.");
        return "index.html";
    }
}
