package com.Boxreview.demo.servicios;

import com.Boxreview.demo.ErrorServicio.ErrorServicio;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.repositorios.UsuarioRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crear(String nombre, String apellido, String email, String contrasenia ) throws ErrorServicio {

        validar(nombre, apellido, email, contrasenia);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setAlta(Boolean.TRUE);

        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void modificarNombre(String nombre, String id) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro el usuario deseado");
        }
    }

    private void validar(String nombre, String apellido, String email, String contrasenia) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (email == null || email.isEmpty()) {
            throw new ErrorServicio("El email no puede ser nulo");
        }
        if (contrasenia == null || contrasenia.isEmpty() || contrasenia.length() <= 6) {
            throw new ErrorServicio("La contraseña no debe estar vacia y debe tener mas de 6 caracteres");
        }
    }
}
