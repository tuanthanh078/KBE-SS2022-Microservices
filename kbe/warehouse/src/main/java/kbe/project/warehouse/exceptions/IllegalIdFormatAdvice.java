package kbe.project.warehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalIdFormatAdvice {
    @ResponseBody
    @ExceptionHandler(IllegalIdFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String illegalIdFormatHandler(IllegalIdFormatException e){
        return e.getMessage();
    }
}
