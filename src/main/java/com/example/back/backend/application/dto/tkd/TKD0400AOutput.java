package com.example.back.backend.application.dto.tkd;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class TKD0400AOutput {

    private List<HashMap<String,Object>> resultList = new ArrayList<>();

}
