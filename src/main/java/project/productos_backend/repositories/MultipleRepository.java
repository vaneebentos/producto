package project.productos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.productos_backend.models.Multiple;


@Repository

public interface MultipleRepository extends JpaRepository <Multiple, Long>{
    
}
