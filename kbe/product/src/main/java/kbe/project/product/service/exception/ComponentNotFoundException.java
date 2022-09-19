package kbe.project.product.service.exception;

import java.util.UUID;

public class ComponentNotFoundException extends RuntimeException {

    public ComponentNotFoundException(UUID id) {
        super("Could not find component " + id);
    }
}
