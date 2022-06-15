                
package com.Boxreview.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Boxreview.demo.entidades.Foto;
import org.springframework.stereotype.Repository;



@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String>{

    
}
