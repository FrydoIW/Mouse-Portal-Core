package com.example.back.backend.infrastructure.entity.PK;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MemberCredentialPk implements Serializable {
    private String refNo;
    private String passwordHash;
}
