package kbe.project.price;

import lombok.Value;

import java.io.Serializable;

@Value
public class Price implements Serializable {
    private Double price;
    private String currency;
}
