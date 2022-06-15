package com.Boxreview.demo.repositorios;

import com.Boxreview.demo.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT c FROM Usuario c WHERE c.nombre = :nombre")
    public Optional<Usuario> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Usuario c WHERE c.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
}
