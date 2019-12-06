package com.kanivets.shares.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Data
@Entity
@EqualsAndHashCode
@Table(name = "shares")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // текстове поле -  "Коментар"
    private String comment;


    //десяткові поля - "Загальна номінальна вартість"*, "Номінальна вартість", "Сплачене державне мито"
    @NotNull
    @Positive
    private Long totalFaceValue;

    @NotNull
    @Positive
    private Long faceValue;

    @NotNull
    @Positive
    private Long paidStateDuty;


    //цілочисельні поля - "Розмір статутного капіталу", "ЄДРПОУ установи", "Кількість"
    @NotNull
    @Positive
    private Long authorizedCapitalSize;

    @NotNull
    @Positive
    private Long codeEDRPOU;

    @NotNull
    @Positive
    private Long amount;


    //дата - "Дата випуску"
    private Date releaseDate;

    public Share(String comment, @NotNull @Positive Long faceValue, @NotNull @Positive Long paidStateDuty,
                 @NotNull @Positive Long authorizedCapitalSize, @NotNull @Positive Long codeEDRPOU,
                 @NotNull @Positive Long amount, Date releaseDate) {
        this.comment = comment;
        this.totalFaceValue = amount*faceValue;
        this.faceValue = faceValue;
        this.paidStateDuty = paidStateDuty;
        this.authorizedCapitalSize = authorizedCapitalSize;
        this.codeEDRPOU = codeEDRPOU;
        this.amount = amount;
        this.releaseDate = releaseDate;
    }
}

