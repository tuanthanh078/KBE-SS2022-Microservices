package kbe.project.product.service.advice;

import kbe.project.product.service.exception.HardwareNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HardwareNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(HardwareNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hardwareNotFoundHandler(HardwareNotFoundException ex) {
        return ex.getMessage();
    }
}
