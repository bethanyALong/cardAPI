package com.example.demo.services.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Pattern(regexp = "(^[0-9]*$)")
    @NotNull
    public String doorNumber;

    @Pattern(regexp = "(^[a-zA-Z]+$)")
    @NotNull
    public String streetName;

    @Pattern(regexp = "(^[a-zA-Z]+$)")
    @NotNull
    public String county;

    @Pattern(regexp = "([([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})])")
    @NotNull
    public String postCode;
}
