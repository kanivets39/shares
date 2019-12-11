package com.kanivets.shares.models;


import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Data
@Entity
@Audited
@EqualsAndHashCode
@RequiredArgsConstructor
@Table(name = "shares")
public class Share {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    // текстове поле -  "Коментар"
    @NotNull
    private String comment;

    //десяткові поля - "Загальна номінальна вартість"*, "Номінальна вартість", "Сплачене державне мито"
    @Positive
    private Long totalFaceValue;

    @NotNull
    @Positive
    @Column(name = "face_Value")
    private Long faceValue;

    @NotNull
    @Positive
    private Long paidStateDuty;

    //цілочисельні поля - "Розмір статутного капіталу", "ЄДРПОУ установи", "Кількість"
    @NotNull
    @Positive
    private Long authorizedFundSize;

    @NotNull
    @Positive
    private Long codeEDRPOU;

    @Positive
    @NotNull
    @Column(name = "amount")
    private Long amount;

    //дата - "Дата випуску"
    @NotNull
    private Date releaseDate;

    public Share( @NotNull String comment, @NotNull @Positive Long faceValue, @NotNull @Positive Long paidStateDuty,
                 @NotNull @Positive Long authorizedCapitalSize, @NotNull @Positive Long codeEDRPOU,
                 @NotNull @Positive Long amount,@NotNull Date releaseDate) {
        this.comment = comment;
        this.totalFaceValue = amount * faceValue;
        this.faceValue = faceValue;
        this.paidStateDuty = paidStateDuty;
        this.authorizedFundSize = authorizedCapitalSize;
        this.codeEDRPOU = codeEDRPOU;
        this.amount = amount;
        this.releaseDate = releaseDate;
    }

    @PostLoad
    private void onLoad() {
        this.totalFaceValue = faceValue * amount;
    }


}

