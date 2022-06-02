package com.Boxreview.demo.entidades;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pelicula {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;
    private String genero;
    private String director;
    private Integer duracion;
    private Integer año;
    private String promedio;
    
    @OneToMany
    private List<Resena> resenas;

   
    
    public String getId() {
        return id;
    }

   
    public void setId(String id) {
        this.id = id;
    }

   
    public String getTitulo() {
        return titulo;
    }

   
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   
    public String getGenero() {
        return genero;
    }

 
    public void setGenero(String genero) {
        this.genero = genero;
    }

  
    public String getDirector() {
        return director;
    }

 
    public void setDirector(String director) {
        this.director = director;
    }

 
    public Integer getDuracion() {
        return duracion;
    }

 
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }


    public Integer getAño() {
        return año;
    }


    public void setAño(Integer año) {
        this.año = año;
    }


    public String getPromedio() {
        return promedio;
    }


    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
    
    
    
    
}
