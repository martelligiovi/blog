package com.system.blog.excepciones;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){
        super(String.format("%s no encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
