package com.Boxreview.demo.servicios;

import com.Boxreview.demo.ErrorServicio.ErrorServicio;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.repositorios.UsuarioRepositorio;
import com.Boxreview.demo.entidades.Foto;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @Autowired
    private NotificacionServicio notificacionServicio;

    @Transactional
    public void crear(MultipartFile archivo,String nombre, String apellido, String email, String contrasenia ) throws ErrorServicio, Exception {

        validar(nombre, apellido, email, contrasenia);

        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setAlta(Boolean.TRUE);
        
        
        
        Foto foto = fotoServicio.guardar(archivo);
        usuario.setFoto(foto);
        
        

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
            if(usuario.getFoto() != null){
                idFoto = usuario.getFoto().getId();              
            }
            
            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            usuario.setFoto(foto);
            

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro el usuario deseado");
        }
    }
    
    public Usuario buscarUsuario(String id, String nombre){
        
         Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {

            return usuarioRepositorio.buscarPorNombre(nombre); 
            
        } else {
            throw new ErrorServicio("");
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
//<<<<<<< HEAD
//    
//    
//=======
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         Usuario usuario = usuarioRepositorio.buscarPorMail(email);
//         if(usuario != null){
//             User user = new User(usuario.getMail(),usuario.getClave(),)
//         }
//    }
//>>>>>>> ed3309c098bc6eba80fc92ed17ab48c71c80f37e
        
}
