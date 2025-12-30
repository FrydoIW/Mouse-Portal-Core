package com.example.back.backend.application.dto.email;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EMA0100AInput {

    @NotNull
    String adminEmail;

}
