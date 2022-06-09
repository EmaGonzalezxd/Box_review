package com.Boxreview.demo.servicios;

import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.repositorios.UsuarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void crear(String nombre, String email, String contrasenia, String apellido) throws Exception {

        validar(nombre, apellido, email, contrasenia);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setAlta(Boolean.TRUE);

        usuarioRepositorio.save(usuario);
    }

    public void modificarNombre(String nombre, String id) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre no puede ser nulo");
        }

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);

            usuarioRepositorio.save(usuario);
        } else {
            throw new Exception("No se encontro el usuario deseado");
        }
    }

    private void validar(String nombre, String apellido, String email, String contrasenia) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("El nombre no puede ser nulo");
        }
        if (email == null || email.isEmpty()) {
            throw new Exception("El email no puede ser nulo");
        }
        if (contrasenia == null || contrasenia.isEmpty() || contrasenia.length() <= 6) {
            throw new Exception("La contraseña no debe estar vacia y debe tener mas de 6 caracteres");
        }
    }
}
