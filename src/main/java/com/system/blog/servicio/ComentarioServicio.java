package com.system.blog.servicio;

import com.system.blog.DTO.ComentarioDTO;

import java.util.List;

public interface ComentarioServicio {
    public ComentarioDTO crearComentario(Long id, ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> obtenerComentariosPorPublicacion(Long id);
    public ComentarioDTO obtenerComentarioPorId(Long id);
    public ComentarioDTO actualizarComentario(Long id, ComentarioDTO comentarioDTO);
    public void eliminarComentario(Long id);
}
