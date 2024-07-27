package com.system.blog.servicio;

import com.system.blog.DTO.PublicacionDTO;
import com.system.blog.DTO.PublicacionRespuestaDTO;


public interface PublicacionServicio {
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public PublicacionRespuestaDTO obtenerPublicaciones(int pageNumber, int pageSize, String sortBy, String sortDir);
    public PublicacionDTO obtenerPublicacionPorId(Long id);
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);
    public void eliminarPublicacion(Long id);
}
