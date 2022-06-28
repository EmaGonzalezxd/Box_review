package com.Boxreview.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServicio {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void enviar(String titulo, String cuerpo, String mail) {
        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setTo(mail);
        mensaje.setFrom("noreply@boxreview.com");
        mensaje.setSubject(titulo);
        mensaje.setText(cuerpo);
        
        mailSender.send(mensaje);
    }

}
