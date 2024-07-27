package com.system.blog.controlador;

import com.system.blog.DTO.PublicacionDTO;
import com.system.blog.DTO.PublicacionRespuestaDTO;
import com.system.blog.servicio.PublicacionServicio;
import com.system.blog.utilerias.AppConstantes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicacion")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PublicacionControlador {
    @Autowired
    private PublicacionServicio publicacionServicio;

    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PublicacionRespuestaDTO> obtenerPublicaciones(@RequestParam(value="pagenumber", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) Integer pageNumber,
                                                                        @RequestParam(value="pagesize", defaultValue = AppConstantes.TAMAÑO_DE_PAGINA_POR_DEFECTO,required = false) Integer pageSize,
                                                                        @RequestParam(value="sortby", defaultValue = AppConstantes.ORDENAR_POR_POR_DEFECTO,required = false) String sortBy,
                                                                        @RequestParam(value="sortdir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO,required = false) String sortDir){
        return new ResponseEntity<>(publicacionServicio.obtenerPublicaciones(pageNumber,pageSize,sortBy,sortDir), org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable Long id){
        return new ResponseEntity<>(publicacionServicio.obtenerPublicacionPorId(id), org.springframework.http.HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO, @Valid @PathVariable Long id){
        PublicacionDTO publicacionActualizada = publicacionServicio.actualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionActualizada, org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@Valid @PathVariable Long id){
        publicacionServicio.eliminarPublicacion(id);
        return new ResponseEntity<>("publicacion eliminada correctamente",org.springframework.http.HttpStatus.NO_CONTENT);
    }

}
