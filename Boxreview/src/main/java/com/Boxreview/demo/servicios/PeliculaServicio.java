
package com.Boxreview.demo.servicios;

import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.repositorios.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServicio {
    
    
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    
    public void cargar(String titulo, String genero, String director, Integer duracion, Integer año){
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo (titulo);
        pelicula.setGenero(genero);
        pelicula.setDirector(director);
        pelicula.setAño(año);
        pelicula.setDuracion(duracion);
        
        peliculaRepositorio.save(pelicula); 
        
    }
    
}
