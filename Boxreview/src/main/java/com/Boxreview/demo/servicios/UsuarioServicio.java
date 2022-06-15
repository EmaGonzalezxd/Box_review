package com.Boxreview.demo.servicios;

import com.Boxreview.demo.ErrorServicio.ErrorServicio;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.repositorios.UsuarioRepositorio;
import com.Boxreview.demo.entidades.Foto;
import com.Boxreview.demo.enumerations.EnumRol;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service

public class UsuarioServicio implements UserDetailsService {


    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private FotoServicio fotoServicio;
    
    @Autowired
    private NotificacionServicio notificacionServicio;

    @Transactional
    public void crear(MultipartFile archivo, String nombre, String apellido, String email, String contrasenia) throws ErrorServicio, Exception {

        validar(nombre, apellido, email, contrasenia);

        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setAlta(Boolean.TRUE);
        usuario.setRol(EnumRol.USUARIO);
        
        String contraEncriptada = new BCryptPasswordEncoder().encode(contrasenia);
        usuario.setContrasenia(contraEncriptada);
        
//        Foto foto = fotoServicio.guardar(archivo);
//        usuario.setFoto(foto);

        usuarioRepositorio.save(usuario);
        
        notificacionServicio.enviar("Bienvenido a BoxReview", "Te has registrado con éxito a BoxReview!!", usuario.getEmail());
    }

    @Transactional
    public void modificarNombre(MultipartFile archivo, String nombre, String id) throws ErrorServicio, Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);

            String idFoto = null;
            if (usuario.getFoto() != null) {
                idFoto = usuario.getFoto().getId();
            }

            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            usuario.setFoto(foto);

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro el usuario deseado");
        }
    }

    public void iniciarSesion(String nombre, String contrasenia) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }

        Optional<Usuario> respuesta = usuarioRepositorio.buscarPorNombre(nombre);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
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
        if (contrasenia == null || contrasenia.isEmpty() || contrasenia.length() < 6) {
            throw new ErrorServicio("La contraseña no debe estar vacia y debe tener mas de 6 caracteres");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            GrantedAuthority p1  = new SimpleGrantedAuthority("ROLE_"+ usuario.getRol());
            permisos.add(p1);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            
            User user = new User(usuario.getEmail(), usuario.getContrasenia(), permisos);
            return user;
        } else {
            return null;
        }
    }


}
