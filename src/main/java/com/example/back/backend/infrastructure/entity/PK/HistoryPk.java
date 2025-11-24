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
public class HistoryPk implements Serializable {
    private String hisType;
    private Integer hisNo;
    private String refNo;
}
