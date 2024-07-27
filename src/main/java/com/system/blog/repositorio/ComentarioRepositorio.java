package com.system.blog.repositorio;


import com.system.blog.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByPublicacion_id(Long publicacion_id);
}
