package com.system.blog.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "cuerpo", nullable = false, length = 50)
    private String cuerpo;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;


}
