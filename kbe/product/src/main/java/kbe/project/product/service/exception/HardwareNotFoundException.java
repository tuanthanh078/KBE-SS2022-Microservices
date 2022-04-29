package kbe.project.product.service.exception;

public class HardwareNotFoundException extends RuntimeException {

    public HardwareNotFoundException(Long id) {
        super("Could not find hardware " + id);
    }
}
