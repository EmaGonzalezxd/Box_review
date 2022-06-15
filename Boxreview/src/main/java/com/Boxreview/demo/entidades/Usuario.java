package com.Boxreview.demo.entidades;

<<<<<<< HEAD
import com.Boxreview.demo.enumerations.EnumRol;
import lombok.Data;
=======

>>>>>>> 91e735303d33ee9062e784c16af8a8469fa83cda

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private Boolean alta;
    
    @Enumerated(EnumType.STRING)
    private EnumRol rol;
    
    @OneToOne
    private Foto foto;


}
