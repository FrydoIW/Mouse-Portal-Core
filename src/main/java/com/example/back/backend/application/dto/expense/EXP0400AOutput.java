package com.example.back.backend.application.dto.expense;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EXP0400AOutput {

    private List<HashMap<String,Object>> resultList = new ArrayList<>();


}
