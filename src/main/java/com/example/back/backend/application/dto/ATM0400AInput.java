package com.example.back.backend.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ATM0400AInput {

    @NotNull
    private Long atmId;

}
