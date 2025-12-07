package com.example.back.backend.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TKD0100AInput {

    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String gender;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String position;
    @NotNull
    private String email;
    private String passwordCredential;

}
