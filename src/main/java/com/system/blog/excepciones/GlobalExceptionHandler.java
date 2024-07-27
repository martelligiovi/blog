package com.system.blog.excepciones;

import com.system.blog.DTO.ErrorDetalles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles();
        errorDetalles.setMaracDetiempo(new Date());
        errorDetalles.setMensaje(exception.getMessage());
        errorDetalles.setDetalles(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, org.springframework.http.HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppExeption.class)
    public ResponseEntity<ErrorDetalles> manejarBloggAppException(BlogAppExeption exception, WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles();
        errorDetalles.setMaracDetiempo(new Date());
        errorDetalles.setMensaje(exception.getMessage());
        errorDetalles.setDetalles(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetalles errorDetalles = new ErrorDetalles();
        errorDetalles.setMaracDetiempo(new Date());
        errorDetalles.setMensaje(exception.getMessage());
        errorDetalles.setDetalles(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();

            errores.put(campo, mensaje);
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
