package com.b5f1.atention.domain.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyItemCreateResponseDto {

    // 아이템 이름
    private String name;
    // 아이템 이미지
    private String itemImage;


}
