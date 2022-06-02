package com.Boxreview.demo.servicios;

import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void crear(String nombre, String email, String contrasenia) throws Exception {

        validar(nombre, email, contrasenia);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setAlta(Boolean.TRUE);

        usuarioRepositorio.save(usuario);
    }

    private void validar(String nombre, String email, String contrasenia) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El ISBN no puede ser nulo");
        }
        if (email == null || email.isEmpty()) {
            throw new Exception("El titulo no puede ser nulo");
        }
        if (contrasenia == null || contrasenia.isEmpty()) {
            throw new Exception("El año de publicacion debe indicarse");
        }
    }
}
