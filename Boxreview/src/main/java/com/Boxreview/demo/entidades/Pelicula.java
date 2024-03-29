package com.Boxreview.demo.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Pelicula {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;
    private String genero;
    private String director;
    private Integer duracion;
    private String anio;
    private String promedio;
    
    @OneToMany
    private List<Resena> resenas;
    
    @OneToOne
    private Foto foto;

   
    
    
    
    
    
}
