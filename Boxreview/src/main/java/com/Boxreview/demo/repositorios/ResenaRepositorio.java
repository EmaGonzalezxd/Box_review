package com.Boxreview.demo.repositorios;

import com.Boxreview.demo.entidades.Resena;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaRepositorio extends JpaRepository<Resena, String> {
    
    @Query("SELECT c FROM Resena c WHERE c.titulo = :titulo")
    public Resena buscarPorNombre(@Param("titulo") String titulo);
    
    @Query("SELECT c FROM Resena c WHERE c.usuario.id = :id")
    public List<Resena> buscarResenaPorId(@Param("id") String id);
    
}
