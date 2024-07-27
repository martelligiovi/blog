package com.system.blog.excepciones;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BlogAppExeption extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus estado;
    private String mensaje;

    public BlogAppExeption(HttpStatus estado, String mensaje, String mensaje2) {
        super();
        this.estado = estado;
        this.mensaje = mensaje2;

    }
}
