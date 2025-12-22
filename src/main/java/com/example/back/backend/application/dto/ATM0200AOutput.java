package com.example.back.backend.application.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ATM0200AOutput {

    private List<HashMap<String,Object>> resultList = new ArrayList<>();

}
