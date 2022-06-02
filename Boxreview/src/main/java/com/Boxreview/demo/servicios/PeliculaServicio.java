
package com.Boxreview.demo.servicios;

import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.repositorios.PeliculaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServicio {
    
    
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    
    public void cargar(String titulo, String genero, String director, Integer duracion, Integer año)throws Exception {
        
        validar (titulo, genero, director, duracion, año);
        
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo (titulo);
        pelicula.setGenero(genero);
        pelicula.setDirector(director);
        pelicula.setAño(año);
        pelicula.setDuracion(duracion);
        
        peliculaRepositorio.save(pelicula);
    }
        
        
        
        
    public void modificar(String id, String titulo, String genero, String director, Integer duracion, Integer año)throws Exception{
        
        validar (titulo, genero, director, duracion, año);
        
        
        Optional <Pelicula> respuesta = peliculaRepositorio.findById(id);
        if(respuesta.isPresent()){
            Pelicula pelicula = respuesta.get();
            pelicula.setTitulo(titulo);
            pelicula.setGenero(genero);
            pelicula.setDirector(director);
            pelicula.setAño(año);
            pelicula.setDuracion(duracion);
        
            peliculaRepositorio.save(pelicula);
        } else {
            throw new Exception("No se encontro la pelicula");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void validar (String titulo, String genero, String director, Integer duracion, Integer año)throws Exception{
        
        
        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("Coloque el titulo de la pelicula");
        }
        if (genero == null || genero.isEmpty()) {
            throw new Exception("Coloque el genero de la pelicula");
        }
        if (director == null || director.isEmpty()) {
            throw new Exception("Coloque el director de la pelicula");
        }
        if (año == null || director.isEmpty()) {
            throw new Exception("Coloque el año de la pelicula");
        }
        if (duracion == null || director.isEmpty()) {
            throw new Exception("Coloque la duracion de la pelicula");
        }  
    }
        
        

        
        
    }
    

