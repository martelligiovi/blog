package com.system.blog.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorDetalles {
    private Date maracDetiempo;
    private String mensaje;
    private String detalles;
}
