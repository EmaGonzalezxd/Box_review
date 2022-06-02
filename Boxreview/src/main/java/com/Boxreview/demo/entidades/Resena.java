package com.Boxreview.demo.entidades;

//import lombok.Data; 
import com.Boxreview.demo.enumerations.EnumCalificacion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
//@Data
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public EnumCalificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(EnumCalificacion calificacion) {
        this.calificacion = calificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "Resena{" + "id=" + id + ", titulo=" + titulo + ", comentario=" + comentario + ", calificacion=" + calificacion + ", usuario=" + usuario + ", pelicula=" + pelicula + '}';
    }
    
    

}
