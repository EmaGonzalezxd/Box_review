package com.Boxreview.demo.servicios;

import com.Boxreview.demo.enumerations.EnumCalificacion;
import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.entidades.Resena;
import com.Boxreview.demo.repositorios.ResenaRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResenaServicio {

    @Autowired
    private ResenaRepositorio resenaRepo;

    @Transactional
    public Resena crear(String id, String titulo, String comentario,
            EnumCalificacion Calificacion, Usuario usuario, Pelicula pelicula) {

        Resena resena = new Resena();
        resena.setUsuario(usuario);
        resena.setPelicula(pelicula);
        resena.setTitulo(titulo);
        resena.setComentario(comentario);
        resena.setCalificacion(Calificacion);

        return resenaRepo.save(resena);
    }

    @Transactional
    public void modificar(String id, Usuario usuario, Pelicula pelicula,
            String titulo, String comentario, EnumCalificacion Calificacion) {

        Optional<Resena> resp = resenaRepo.findById(id);

        if (resp.isPresent()) {

            Resena resena = new Resena();

            resena.setPelicula(pelicula);
            resena.setTitulo(titulo);
            resena.setComentario(comentario);
            resena.setCalificacion(Calificacion);
            
            resenaRepo.save(resena); 
        } else {
            throw new Exception; 
        }
        
        
    }
}
