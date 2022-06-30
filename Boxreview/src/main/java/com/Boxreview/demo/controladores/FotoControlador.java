package com.Boxreview.demo.controladores;

import com.Boxreview.demo.ErrorServicio.ErrorServicio;
import com.Boxreview.demo.entidades.Usuario;
import com.Boxreview.demo.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<byte[]> fotoUsuario(@PathVariable String id) throws ErrorServicio {

        Usuario usuario = usuarioServicio.buscarPorId(id);
        if (usuario.getFoto() == null) {
            throw new ErrorServicio("El Usuario no tiene foto asignada");
        }
        byte[] foto = usuario.getFoto().getContenido(); 

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(foto, headers, HttpStatus.OK);

    }

}

