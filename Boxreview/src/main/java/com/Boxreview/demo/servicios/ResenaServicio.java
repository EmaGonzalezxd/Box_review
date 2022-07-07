package com.Boxreview.demo.servicios;

import com.Boxreview.demo.ErrorServicio.ErrorServicio;
import com.Boxreview.demo.enumerations.EnumCalificacion;
import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.entidades.Resena;
import com.Boxreview.demo.repositorios.ResenaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResenaServicio {
    
    @Autowired
    private ResenaRepositorio resenaRepositorio;
    
    @Transactional
    public void crear(String titulo, String comentario,
            EnumCalificacion calificacion, Usuario usuario, Pelicula pelicula) throws ErrorServicio {
        
        validar(titulo, comentario, calificacion, usuario, pelicula);
        
        Resena resena = new Resena();
        resena.setUsuario(usuario);
        resena.setPelicula(pelicula);
        resena.setTitulo(titulo);
        resena.setComentario(comentario);
        resena.setCalificacion(calificacion);
        
        resenaRepositorio.save(resena);
    }
    
    @Transactional
    public void modificar(String id, Usuario usuario, Pelicula pelicula,
            String titulo, String comentario, EnumCalificacion Calificacion) throws ErrorServicio {
        
        Optional<Resena> respuesta = resenaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Resena resena = respuesta.get();
            
            resena.setPelicula(pelicula);
            resena.setTitulo(titulo);
            resena.setComentario(comentario);
            resena.setCalificacion(Calificacion);
            
            resenaRepositorio.save(resena);
        } else {
            throw new ErrorServicio("No se encontro la reseña");
        }
        
    }
    
    @Transactional
    public void eliminar(String id) {
        
        resenaRepositorio.deleteById(id);
    }
    
    public void validar(String titulo, String comentario,
            EnumCalificacion Calificacion, Usuario usuario, Pelicula pelicula) throws ErrorServicio {
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo");
        }
        if (comentario == null || comentario.isEmpty()) {
            throw new ErrorServicio("El comentario no puede ser nulo");
        }
        if (Calificacion == null) {
            throw new ErrorServicio("La calificacion no puede ser nulo");
        }
        if (usuario == null) {
            throw new ErrorServicio("El usuario no puede ser nulo");
        }
        if (pelicula == null) {
            throw new ErrorServicio("La pelicula no puede ser nulo");
        }
    }
    
    public List<Resena> buscarResenaPorUsuario(String id){
        return resenaRepositorio.buscarPorId(id);
    }
    
    public List<Resena> buscarResenaPorPelicula(String id){
        return resenaRepositorio.buscarPorPelicula(id);
    }
    
    @Transactional
    public List<Resena> mostrarTodos(){
        return resenaRepositorio.findAll();
    }
}
