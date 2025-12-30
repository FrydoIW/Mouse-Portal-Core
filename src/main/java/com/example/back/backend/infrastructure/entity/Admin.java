package com.example.back.backend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADMIN")
@ToString
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String address;
    String gender;
    String email;
    Boolean emailVerification;
    String verificationToken;
    String passwordHash;
    String twoFactorSecret;
    String profilePicture;
    LocalDate regDt;
    LocalDate updDt;
}
