package com.Boxreview.demo.controladores;

import com.Boxreview.demo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
//@RequestMapping("/crear")
//public class ControladorUsuario {
//
//    @Autowired
//    private UsuarioServicio usuarioServicio;
//
//    @PostMapping("/registrar")
//    public String registrar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia) {
//
//        System.out.println("Nombre: " + nombre);
//        System.out.println("Apellido: " + apellido);
//        System.out.println("Email: " + email);
//        System.out.println("contra: " + contrasenia);
//        
//        return "index.html";
//    }
////        try {
////            usuarioServicio.crear(nombre, apellido, email, contrasenia);
////        } catch (Exception ex) {
//////            modelo.put("error", ex.getMessage());
////            return "Registro.html";
////        }
//////        modelo.put("titulo", "Felicidades!");
//////        modelo.put("descripcion", "Autor registrado satisfactoriamente.");
////        return "index.html";
//    
//}
