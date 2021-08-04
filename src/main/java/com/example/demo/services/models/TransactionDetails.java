package com.example.demo.services.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "TRANSACTION_DETAILS_TBL")
public class TransactionDetails{

//    private UserDetails userDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int transactionID;

    @Column(name = "transaction_date_time")
    @JsonProperty(value = "Transaction date time")
    @NotNull
    private String transaction_date_time;

    @Column(name = "amount_total")
    @JsonProperty(value = "Amount total")
    @NotNull
    private Integer amount_total;

    @Column(name = "amount_payable")
    @JsonProperty(value = "Amount payable")
    @NotNull
    private Integer amount_payable;

    @Column(name = "merchant_code")
    @JsonProperty(value = "Merchant code")
    @NotNull
    private String merchant_code;

//    @ManyToOne
//    @JoinColumn(name = "user_details")
//    public UserDetails getUserDetails(){
//        return userDetails;
//    }
}
