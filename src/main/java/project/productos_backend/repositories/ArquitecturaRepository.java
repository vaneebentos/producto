package project.productos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.productos_backend.models.Arquitectura;

@Repository
public interface ArquitecturaRepository extends JpaRepository <Arquitectura,Long>{
    
}
