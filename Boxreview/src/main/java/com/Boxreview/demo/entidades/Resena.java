package com.Boxreview.demo.entidades;

import lombok.Data; 
import com.Boxreview.demo.enumerations.EnumCalificacion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
public class Resena {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id; 
    
    private String titulo; 

    private String comentario; 

    private EnumCalificacion calificacion; 
    
    @OneToOne
    private Usuario usuario; 
    
    @OneToOne
    private Pelicula pelicula; 
    
    

}
