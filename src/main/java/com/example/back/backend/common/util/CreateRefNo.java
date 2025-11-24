package com.example.back.backend.common.util;

import com.example.back.backend.infrastructure.jpa.DaoMemberJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
public class CreateRefNo {

    private final DaoMemberJpa memberJpa;

    public String makeRef() throws Exception{

        String uniqueRef = createRandomDigit(10);

        while(memberJpa.existsById(uniqueRef)){

            uniqueRef = createRandomDigit(10);

        }

        return uniqueRef;

    }

    public String createRandomDigit(int digits) throws Exception {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10)); // 0–9
        }

        return sb.toString();

    }

}
