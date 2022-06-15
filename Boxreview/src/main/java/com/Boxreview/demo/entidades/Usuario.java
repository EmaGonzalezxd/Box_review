package com.Boxreview.demo.entidades;


import com.Boxreview.demo.enumerations.EnumRol;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
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
