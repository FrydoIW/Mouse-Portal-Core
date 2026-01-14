package com.example.back.backend.application.dto.atm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ATM0500AInput {

    private Long atmId;
    private byte[] ktpImage;

}
