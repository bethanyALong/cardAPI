package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @NotNull
    private Integer doorNumber;

    @Pattern(regexp = "(^[a-zA-Z]+$)")
    @NotNull
    private String streetName;

    @Pattern(regexp = "(^[a-zA-Z]+$)")
    @NotNull
    private String county;

    @Pattern(regexp = "([([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})])")
    @NotNull
    private String postCode;
}
