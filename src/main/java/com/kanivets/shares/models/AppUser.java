package com.kanivets.shares.models;




import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Data
@Entity
@EqualsAndHashCode
public class AppUser {

    @Id
    @NotNull
   private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

}
