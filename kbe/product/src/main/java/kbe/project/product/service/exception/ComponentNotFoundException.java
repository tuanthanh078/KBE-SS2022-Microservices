package kbe.project.product.service.exception;

public class ComponentNotFoundException extends RuntimeException {

    public ComponentNotFoundException(Long id) {
        super("Could not find component " + id);
    }
}
