package com.kanivets.shares.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivateShareDTO {


    private Long id;
    private String comment;
    private Long totalFaceValue;
    private Long faceValue;
    private Long paidStateDuty;
    private Long authorizedFundSize;
    private Long codeEDRPOU;
    private Long amount;
    private Date releaseDate;

}
