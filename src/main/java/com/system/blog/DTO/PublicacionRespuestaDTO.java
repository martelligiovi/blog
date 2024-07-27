package com.system.blog.DTO;

import lombok.Data;

import java.util.List;
@Data
public class PublicacionRespuestaDTO {
    private List<PublicacionDTO> publicaciones;
    private int numeroPaginas;
    private int tamañoPagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultimaPagina;
}
