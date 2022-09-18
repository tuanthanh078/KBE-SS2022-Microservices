package kbe.project.warehouse.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("Could not find entity with id: \"" + id + "\"");
    }
}
