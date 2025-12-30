package com.example.back.backend.application.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ADM0100AInput {

    @NotNull
    String name;
    @NotNull
    String address;
    @NotNull
    LocalDate birthDt;
    @NotNull
    String gender;
    @NotNull
    String email;
    @NotNull
    String password;

}
