package com.system.blog.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "publicaciones", uniqueConstraints = {@UniqueConstraint(columnNames = "titulo")})
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;
    @Column(name = "contenido", nullable = false, length = 50)
    private String contenido;
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "publicacion")
    private Set<Comentario> comentarios = new HashSet<>();
}
