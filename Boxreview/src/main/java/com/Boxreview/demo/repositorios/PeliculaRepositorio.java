
package com.Boxreview.demo.repositorios;

import com.Boxreview.demo.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, String>{
    
    @Query("SELECT c FROM Pelicula c WHERE c.titulo = :titulo")
    public Pelicula buscarPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT c FROM Pelicula c WHERE c.genero = :genero")
    public Pelicula buscarPorGenero(@Param("genero") String genero);
    
    
    
    
    
}
