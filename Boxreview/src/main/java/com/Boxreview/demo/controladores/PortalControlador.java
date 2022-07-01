package com.Boxreview.demo.controladores;

import com.Boxreview.demo.enumerations.Generos;
import com.Boxreview.demo.servicios.PeliculaServicio;
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

    @Autowired
    private PeliculaServicio peliculaServicio;
    
    @GetMapping("/")
    public String inicio() {
        return "inicio.html";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    
    @GetMapping("/resena")
    public String resena() {
        return "reseña.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam MultipartFile foto, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia) {

        try {

            usuarioServicio.crear(foto, nombre, apellido, email, contrasenia);

            modelo.put("titulo", "Felicidades!");
            modelo.put("descripcion", "Usuario registrado satisfactoriamente.");

            return "inicio.html";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            modelo.put("error", ex.getMessage());
            return "inicio.html";
        }

    }
    
    @GetMapping("/agregarPeli")
    public String agregarPeli(ModelMap modelo) {
        modelo.put("generos", Generos.values());
        return "agregarPeli.html";
        
    }
    
    @PostMapping("/crearPeli")
    public String crearPeli(ModelMap modelo, ModelMap modelo2, @RequestParam MultipartFile foto, @RequestParam String titulo, @RequestParam String genero, @RequestParam String director, @RequestParam Integer duracion, @RequestParam String anio) {

        try {

            
            peliculaServicio.crear(foto, titulo, genero, director, duracion, anio);

            modelo.put("titulo", "Felicidades!");
            modelo.put("descripcion", "Persistida la pelicula con exito.");

            return "agregarPeli.html";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            modelo.put("error", ex.getMessage());
            return "agregarPeli.html";
        }

    }
    
}
