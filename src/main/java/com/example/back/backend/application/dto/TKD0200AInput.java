package com.example.back.backend.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TKD0200AInput {

    String email;
    String password;
    String otp;
    boolean verifyOtp = false;

}
