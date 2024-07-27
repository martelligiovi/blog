package com.system.blog.servicio;

import com.system.blog.DTO.UserDTO;

import java.util.List;

public interface UserServicio {

    public UserDTO crearUser(UserDTO userDTO);
    public UserDTO obtenerUserPorId(Long id);
    public void eliminarUser(Long id);
    public List<UserDTO> obtenerUsers();

    public boolean login(String username, String password);


}
