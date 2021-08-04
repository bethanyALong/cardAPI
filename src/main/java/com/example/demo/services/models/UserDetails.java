package com.example.demo.services.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "USER_DETAILS_TBL")
public class UserDetails {
    //TODO: Check all info

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column(name = "first_name")
    @JsonProperty(value = "first_name")
    @Pattern(regexp = "([a-zA-Z])")
    @Size(min = 1)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty(value = "last_name")
    @Pattern(regexp = "([a-zA-Z])")
    @Size(min = 1)
    @NotNull
    private String lastName;

    @Column(name = "email_address")
    @JsonProperty(value = "email_address")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotNull
    private String emailAddress;

//    @Column(name = "transaction_history")
//    @JsonProperty(value = "transaction_history")
//    private List<TransactionDetails> transactions;

    @Column(name = "long_card_number")
    @JsonProperty(value = "long_card_number")
    @Pattern(regexp = "([0-9])")
    @Size(min = 16, max = 16)
    private String longCardNumber;

    @Column(name = "expiry_date")
    @JsonProperty(value = "expiry_date")
    @Pattern(regexp = "([0-9])")
    @Size(min = 4, max = 4)
    private String expiryDate;

    @Column(name = "cvv")
    @JsonProperty(value = "cvv")
    @Pattern(regexp = "([0-9])")
    @Size(min = 3, max = 3)
    private String cvv;

    @Column(name = "balance")
    @JsonProperty(value = "balance")
    private Integer balance;


//    @OneToMany(targetEntity = TransactionDetails.class,
//            mappedBy = "userDetails",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    public List<TransactionDetails> getTransactionDetails(){
//        return getTransactions();
//    }


}
