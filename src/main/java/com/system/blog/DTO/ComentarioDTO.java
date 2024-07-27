package com.system.blog.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ComentarioDTO {
    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "El email no puede estar vacio")

    private String email;
    @NotEmpty(message = "El cuerpo no puede estar vacio")
    private String cuerpo;
    private PublicacionDTO publicacion;

    public ComentarioDTO(String nombre, String email, String cuerpo, long idPublicacion) {
        this.nombre = nombre;
        this.email = email;
        this.cuerpo = cuerpo;
        this.publicacion = new PublicacionDTO(idPublicacion);
    }
    public ComentarioDTO() {
    }
}
