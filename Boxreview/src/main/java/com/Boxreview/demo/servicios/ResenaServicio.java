

package com.Boxreview.demo.servicios;

import com.Boxreview.demo.enumerations.EnumCalificacion;
import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.entidades.Resena;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResenaServicio {

    @Autowired  
    private ResenaRepositorio resenaRepo; 
    
    @Transactional
    public Resena crear(String id, String titulo, String comentario, 
            EnumCalificacion enumCalificacion, Usuario usuario, Pelicula pelicula){
        
        Resena resena= new Resena(); 
        resena.setUsuario(usuario);
        resena.setPelicula(pelicula);
        resena.setTitulo(titulo);
        resena.setComentario(comentario);
        resena.setCalificacion(enumCalificacion);
        
        return resenaRepo.save(resena); 
    }
}
