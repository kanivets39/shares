package com.kanivets.shares.dto;

import lombok.*;


import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicShareDTO {


    private Long id;
    private Long codeEDRPOU;
    private Long amount;
    private Long totalFaceValue;
    private Long faceValue;
    private Date releaseDate;

}
