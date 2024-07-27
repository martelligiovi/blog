package com.system.blog.servicio;

import com.system.blog.DTO.PublicacionDTO;
import com.system.blog.DTO.PublicacionRespuestaDTO;
import com.system.blog.entidades.Publicacion;
import com.system.blog.excepciones.ResourceNotFoundException;
import com.system.blog.repositorio.PublicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class PublicacionServicioImpl implements PublicacionServicio{

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        //convertimos  de DTO a entidad
        Publicacion publicacion = convertirDTOAEntidad(publicacionDTO);
        //guardamos la entidad
        Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);
        //convertimos de entidad a DTO
        PublicacionDTO publicacionRespuesta = convertirEntidadADTO(nuevaPublicacion);

        return publicacionRespuesta;
    }
    private PublicacionDTO convertirEntidadADTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setContenido(publicacion.getContenido());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        return publicacionDTO;
    }

    private Publicacion convertirDTOAEntidad(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();
        publicacion.setId(publicacionDTO.getId());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        return publicacion;
    }

    public PublicacionRespuestaDTO obtenerPublicaciones(int pageNumber, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("id").descending() : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Publicacion> publicacionesPaginadas = publicacionRepositorio.findAll(pageable);
        List<Publicacion> publicaciones = publicacionesPaginadas.getContent();

        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();

        for(Publicacion publicacion: publicaciones){
            publicacionesDTO.add(convertirEntidadADTO(publicacion));
        }

        PublicacionRespuestaDTO publicacionRespuestaDTO = new PublicacionRespuestaDTO();
        publicacionRespuestaDTO.setPublicaciones(publicacionesDTO);
        publicacionRespuestaDTO.setNumeroPaginas(publicacionesPaginadas.getNumber());
        publicacionRespuestaDTO.setTamañoPagina(publicacionesPaginadas.getSize());
        publicacionRespuestaDTO.setTotalElementos(publicacionesPaginadas.getTotalElements());
        publicacionRespuestaDTO.setTotalPaginas(publicacionesPaginadas.getTotalPages());
        publicacionRespuestaDTO.setUltimaPagina(publicacionesPaginadas.isLast());
        return publicacionRespuestaDTO;
    }

    public PublicacionDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return convertirEntidadADTO(publicacion);
    }

    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionDTO.getId()));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
        return convertirEntidadADTO(publicacionActualizada);
    }

    @Transactional
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacionRepositorio.delete(publicacion);
    }

}
