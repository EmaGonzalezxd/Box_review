package com.Boxreview.demo.controladores;

import com.Boxreview.demo.ErrorServicio.ErrorServicio;
import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.entidades.Resena;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.enumerations.EnumCalificacion;
import com.Boxreview.demo.enumerations.Generos;
import com.Boxreview.demo.servicios.PeliculaServicio;
import com.Boxreview.demo.servicios.ResenaServicio;
import com.Boxreview.demo.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("")
public class PortalControlador {

    @Autowired
    private ResenaServicio resenaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PeliculaServicio peliculaServicio;

    @GetMapping("/")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/index")
    public String index(ModelMap modelo) {
        
        List<Pelicula> peliculasTerror = peliculaServicio.mostrarTerror();
        modelo.put("peliterro", peliculasTerror);
        
        List<Pelicula> peliculasAccion = peliculaServicio.mostrarAccion();
        modelo.put("peliaccion", peliculasAccion);
        
        List<Pelicula> peliculasBelicas = peliculaServicio.mostrarBelica();
        modelo.put("pelibelica", peliculasBelicas);
        
        return "index.html";
    }

    @GetMapping("/buscador")
    public String buscador(ModelMap model, @RequestParam String titulo) {
        try {
            Pelicula pelicula =peliculaServicio.buscarPeli(titulo);
            
            return "redirect:/resena/" + pelicula.getId();
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", e.getMessage());
            return "index.html";
        }

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

    @GetMapping("/resena/{id}")
    public String resena(ModelMap modelo, @PathVariable String id) throws ErrorServicio {
        Pelicula pelicula = peliculaServicio.buscarPorId(id);
        List<Resena> resenas = resenaServicio.buscarResenaPorPelicula(id);
        modelo.addAttribute("resenas", resenas);
        modelo.addAttribute("pelicula", pelicula);
//        modelo.addAttribute("calificacion",EnumCalificacion.values());
        return "reseña.html";
    }

    @PostMapping("/resenar")
    public String resenar(ModelMap modelo, HttpSession session, @RequestParam String titulo, @RequestParam String comentario,
            @RequestParam EnumCalificacion calificacion, @RequestParam Pelicula pelicula) {
        try {
//            Usuario usuario = usuarioServicio.buscarPorId(session.getId());
            System.out.println(pelicula);
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            resenaServicio.crear(titulo, comentario, calificacion, usuario, pelicula);
            modelo.put("titulo", "Felicidades!");
            modelo.put("descripcion", "Persistida la reseña con exito.");
            return "redirect:/resena/" + pelicula.getId();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            modelo.put("error", ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/resena/" + pelicula.getId();
    }

    //GET DE LA VISTA AGREGAR PELICULA//
    @GetMapping("/agregarPeli")
    public String agregarPeli(ModelMap modelo) {
        modelo.put("generos", Generos.values());
        return "agregarPeli.html";

    }

    //POST FORMULARIO PARA AGREGAR PELICULA//
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

    @GetMapping("/misResenas")
    public String misResenas(HttpSession session, ModelMap modelo) {
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null) {
            return "inicio.html";
        }
        List<Resena> resenas = resenaServicio.buscarResenaPorUsuario(login.getId());
        modelo.put("resenas", resenas);
        return "misResenas.html";
    }

    @PostMapping("/modificarResena")
    public String modificarResena(HttpSession session, @RequestParam String titulo, @RequestParam String comentario,
            @RequestParam EnumCalificacion calificacion, @RequestParam Pelicula pelicula) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            resenaServicio.modificar(titulo, usuario, pelicula, titulo, comentario, calificacion);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return "misResenas";
    }

    @GetMapping("/eliminarResena/{id}")
    public String eliminarResena(@PathVariable String id) {
        try {
            resenaServicio.eliminar(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return "redirect:/misResenas";
    }

    @GetMapping("/miperfil")
    public String miPerfil(HttpSession session, ModelMap modelo) {
        modelo.addAttribute("usuario",session.getAttribute("usuariosession"));
        return "miperfil.html";
    }

    @PostMapping("/editarUsuario")
    public String editarUsuario(HttpSession session, ModelMap modelo, @RequestParam MultipartFile foto, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String contrasenia) {
        
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            usuarioServicio.modificar(usuario.getId(), foto, nombre, apellido, email, contrasenia);
            modelo.put("exito", "!Usuario actualizado¡");
            return "miperfil.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return "miperfil.html";
        }
        
    }

}