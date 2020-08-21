package com.rogeriobento.osworks.domain.repository;

import com.rogeriobento.osworks.domain.model.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    
}