package kbe.project.warehouse.exceptions;

public class IllegalIdFormatException extends RuntimeException{
    public IllegalIdFormatException(String source){
        super("Source could not be identified as UUID: \"" + source + "\"");
    }
}
