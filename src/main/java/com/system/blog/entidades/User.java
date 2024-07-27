package com.system.blog.entidades;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "nombre")})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "password", nullable = false, length = 50)
    private String password;


}
