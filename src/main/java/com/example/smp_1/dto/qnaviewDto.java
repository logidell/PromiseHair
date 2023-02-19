package com.example.smp_1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
//ToString to String을 자동생성하는 롬북어노테이션
@AllArgsConstructor
//생성자를 자동 생성하는 롬북 어노테이션
public class qnaviewDto {
    private int idx;
    private String title;
    private String contents;
    private String createDt;
    private String updateDt;


}
