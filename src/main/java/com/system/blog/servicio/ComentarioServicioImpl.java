package com.system.blog.servicio;

import com.system.blog.DTO.ComentarioDTO;
import com.system.blog.entidades.Comentario;
import com.system.blog.entidades.Publicacion;
import com.system.blog.excepciones.ResourceNotFoundException;
import com.system.blog.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Override
    public ComentarioDTO crearComentario(Long id, ComentarioDTO comentarioDTO) {
        Comentario comentario = convertirAComentario(comentarioDTO);

        Publicacion publicacion = publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepositorio.save(comentario);

        ComentarioDTO nuevoComentarioDTO = convertirAComentarioDTO(nuevoComentario);

        return null;
    }
    private ComentarioDTO convertirAComentarioDTO(Comentario comentario){
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setNombre(comentario.getNombre());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setCuerpo(comentario.getCuerpo());
        return comentarioDTO;
    }
    private Comentario convertirAComentario(ComentarioDTO comentarioDTO){
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        return comentario;
    }

    public List<ComentarioDTO> obtenerComentariosPorPublicacion(Long id) {
        List<Comentario> comentarios = comentarioRepositorio.findByPublicacion_id(id);
        return comentarios.stream().map(comentario -> convertirAComentarioDTO(comentario)).collect(Collectors.toList());
    }

    public ComentarioDTO obtenerComentarioPorId(Long id) {
        Comentario comentario = comentarioRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", id));
        return convertirAComentarioDTO(comentario);
    }

    public ComentarioDTO actualizarComentario(Long id, ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", id));
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        Comentario comentarioActualizado = comentarioRepositorio.save(comentario);
        return convertirAComentarioDTO(comentarioActualizado);
    }

    public void eliminarComentario(Long id) {
        Comentario comentario = comentarioRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", id));
        comentarioRepositorio.delete(comentario);
    }




}
