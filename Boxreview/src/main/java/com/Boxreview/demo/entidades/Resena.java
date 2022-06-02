package com.Boxreview.demo.entidades;

import lombok.Data; 
import com.Boxreview.demo.enumerations.EnumCalificacion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Data
public class Resena {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id; 
    
    @Autowired
    private String titulo; 
    
    @Autowired
    private String comentario; 
    
    @Autowired
    private EnumCalificacion calificacion; 
    
    @Autowired 
    @OneToOne
    private Usuario usuario; 
    
    @Autowired 
    @OneToOne
    private Pelicula pelicula; 
    
    

}
