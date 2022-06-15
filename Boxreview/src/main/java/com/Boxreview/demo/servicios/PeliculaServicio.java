package com.Boxreview.demo.servicios;

import com.Boxreview.demo.entidades.Foto;
import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.repositorios.PeliculaRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public void crear(MultipartFile archivo, String titulo, String genero, String director, Integer duracion, Integer año) throws Exception {

        validar(titulo, genero, director, duracion, año);

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        pelicula.setGenero(genero);
        pelicula.setDirector(director);
        pelicula.setAño(año);
        pelicula.setDuracion(duracion);

        Foto foto = fotoServicio.guardar(archivo);
        pelicula.setFoto(foto);

        peliculaRepositorio.save(pelicula);
    }

    @Transactional
    public void modificar(MultipartFile archivo, String id, String titulo, String genero, String director, Integer duracion, Integer año) throws Exception {

        validar(titulo, genero, director, duracion, año);

        Optional<Pelicula> respuesta = peliculaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Pelicula pelicula = respuesta.get();
            pelicula.setTitulo(titulo);
            pelicula.setGenero(genero);
            pelicula.setDirector(director);
            pelicula.setAño(año);
            pelicula.setDuracion(duracion);

            String idFoto = null;
            if (pelicula.getFoto() != null) {
                idFoto = pelicula.getFoto().getId();
            }

            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            pelicula.setFoto(foto);

            peliculaRepositorio.save(pelicula);
        } else {
            throw new Exception("No se encontro la pelicula");
        }
    }

    public void validar(String titulo, String genero, String director, Integer duracion, Integer año) throws Exception {

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
