package com.system.blog.servicio;

import com.system.blog.DTO.UserDTO;
import com.system.blog.entidades.User;
import com.system.blog.excepciones.ResourceNotFoundException;
import com.system.blog.repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServicioImpl implements UserServicio{

    @Autowired
    private UserRepositorio userRepositorio;

    public UserDTO crearUser(UserDTO userDTO) {
        User user = convertirAUser(userDTO);
        User nuevoUser = userRepositorio.save(user);
        UserDTO nuevoUserDTO = convertirAUserDTO(nuevoUser);
        return nuevoUserDTO;
    }

    public UserDTO obtenerUserPorId(Long id) {
        User user = userRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return convertirAUserDTO(user);
    }

    public void eliminarUser(Long id) {
        userRepositorio.deleteById(id);
    }

    public List<UserDTO> obtenerUsers() {
        List<User> users = userRepositorio.findAll();
        return users.stream().map(user -> convertirAUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public boolean login(String username, String password) {
        return !Objects.isNull(userRepositorio.findByNombreAndPassword(username, password));
    }

    public UserDTO convertirAUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNombre(user.getNombre());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public User convertirAUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setNombre(userDTO.getNombre());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

}
