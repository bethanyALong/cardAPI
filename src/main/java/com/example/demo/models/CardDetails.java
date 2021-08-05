package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDetails {

    @Pattern(regexp = "(^[0-9]*$)")
    @Size(min = 16, max = 16)
    private String longCardNumber;

    @Pattern(regexp = "(^[0-9]*$)")
    @Size(min = 4, max = 4)
    private String expiryDate;

    @Pattern(regexp = "(^[0-9]*$)")
    @Size(min = 3, max = 3)
    private String cvv;

    @Min(value = 0)
    private Integer balance;
}
