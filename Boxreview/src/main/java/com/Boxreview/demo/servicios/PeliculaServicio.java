package com.Boxreview.demo.servicios;

import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.repositorios.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    public void cargar(String titulo, String genero, String director, Integer duracion, Integer año) throws Exception {

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

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        pelicula.setGenero(genero);
        pelicula.setDirector(director);
        pelicula.setAño(año);
        pelicula.setDuracion(duracion);

        peliculaRepositorio.save(pelicula);

    }
}
