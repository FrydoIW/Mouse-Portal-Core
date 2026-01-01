package com.example.back.backend.application.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ADM0500AInput {

    String name;
    LocalDate birthDt;
    String gender;
    @NotNull
    Long id;

}
