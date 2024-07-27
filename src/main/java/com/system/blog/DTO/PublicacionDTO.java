package com.system.blog.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class PublicacionDTO {
    private Long id;
    @NotEmpty(message = "El titulo no puede estar vacio")
    private String titulo;
    @NotEmpty(message = "El contenido no puede estar vacio")
    private String contenido;
    @NotEmpty(message = "La descripcion no puede estar vacia")
    private String descripcion;
    private Set<ComentarioDTO> comentarios;

    public PublicacionDTO(Long id) {
        this.id = id;
    }
    public PublicacionDTO() {
    }
}
