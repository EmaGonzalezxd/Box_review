

package com.Boxreview.demo.entidades;

import javax.persistence.Entity;

@Entity
public class Resena {
    
    @Id
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
