package kbe.project.product.service.advice;

import kbe.project.product.service.exception.ComponentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ComponentNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ComponentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String componentNotFoundHandler(ComponentNotFoundException ex) {
        return ex.getMessage();
    }
}
