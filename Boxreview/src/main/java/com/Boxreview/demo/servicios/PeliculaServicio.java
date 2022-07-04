package com.Boxreview.demo.servicios;

import com.Boxreview.demo.entidades.Foto;
import com.Boxreview.demo.entidades.Pelicula;
import com.Boxreview.demo.repositorios.PeliculaRepositorio;
import java.util.List;
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
    public void crear(MultipartFile archivo, String titulo, String genero, String director, Integer duracion, String anio) throws Exception {

        validar(titulo, genero, director, duracion, anio);

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        pelicula.setGenero(genero);
        pelicula.setDirector(director);
        pelicula.setAnio(anio);
        pelicula.setDuracion(duracion);

        Foto foto = fotoServicio.guardar(archivo);
        pelicula.setFoto(foto);

        peliculaRepositorio.save(pelicula);
    }

    @Transactional
    public void modificar(MultipartFile archivo, String id, String titulo, String genero, String director, Integer duracion, String anio) throws Exception {

        validar(titulo, genero, director, duracion, anio);

        Optional<Pelicula> respuesta = peliculaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Pelicula pelicula = respuesta.get();
            pelicula.setTitulo(titulo);
            pelicula.setGenero(genero);
            pelicula.setDirector(director);
            pelicula.setAnio(anio);
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

    public void validar(String titulo, String genero, String director, Integer duracion, String anio) throws Exception {

        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("Coloque el titulo de la pelicula");
        }
        if (genero == null || genero.isEmpty()) {
            throw new Exception("Coloque el genero de la pelicula");
        }
        if (director == null || director.isEmpty()) {
            throw new Exception("Coloque el director de la pelicula");
        }
        if (anio == null || director.isEmpty()) {
            throw new Exception("Coloque el año de la pelicula");
        }
        if (duracion == null || director.isEmpty()) {
            throw new Exception("Coloque la duracion de la pelicula");
        }
    }
    
    @Transactional
    public List<Pelicula> mostrarTodos(){
        return peliculaRepositorio.findAll();
    }
    
    @Transactional
    public List<Pelicula> buscarPeli(String titulo) throws Exception {

        try {
            List<Pelicula> peliculas = peliculaRepositorio.buscarPorTitulo(titulo);
            return peliculas;
        } catch (Exception e) {
            throw new Exception("Esta pelicula no existe");
        }

    }

}
