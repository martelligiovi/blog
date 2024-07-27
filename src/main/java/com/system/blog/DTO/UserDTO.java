package com.system.blog.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password;

}
