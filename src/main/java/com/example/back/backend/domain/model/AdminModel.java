package com.example.back.backend.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AdminModel {

    private String name;
    private String address;
    private LocalDate birthDt;
    private String gender;
    private String email;
    private String passwordHash;
    private String twoFactorSecret;
    private String profilePicture;


}
