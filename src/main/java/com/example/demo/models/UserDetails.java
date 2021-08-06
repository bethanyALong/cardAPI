package com.example.demo.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_DETAILS_TBL")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotNull
    private String emailAddress;

    @Size(min = 8)
    @NotNull
    private String password;

    @NotNull
    private Boolean newsFeaturesAgreed;

    @Pattern(regexp = "(^[a-zA-Z]+$)")
    @Size(min = 1)
    @NotNull
    private String firstName;

    @Pattern(regexp = "(^[a-zA-Z]+$)")
    @Size(min = 1)
    @NotNull
    private String lastName;

    @NotNull
    private GenderEnum gender;

    @Pattern(regexp = "(^[0-9]*$)")
    @Size(min = 2, max = 2)
    @NotNull
    private String birthDay;

    @Pattern(regexp = "(^[0-9]*$)")
    @Size(min = 2, max = 2)
    @NotNull
    private String birthMonth;

    @Pattern(regexp = "(^[0-9]*$)")
    @Size(min = 4, max = 4)
    @NotNull
    private String birthYear;

    @Embedded
    @NotNull
    private Address address;

    @Embedded
    @NotNull
    private Stores stores;

    @Embedded
    @NotNull
    private CardDetails cardDetails;

    @NotNull
    private String dtype;

}
