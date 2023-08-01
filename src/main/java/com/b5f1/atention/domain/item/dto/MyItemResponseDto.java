package com.b5f1.atention.domain.item.dto;

import com.b5f1.atention.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyItemResponseDto {

    // 아이템 이름
    private String name;
    // 아이템 이미지
    private String image;
    // 아이템 설명
    private String description;

    // item -> itemResponseDto로 변환
    public MyItemResponseDto toItemResponseDto(Item item) {
        return MyItemResponseDto.builder()
                .name(item.getName())
                .image(item.getImage())
                .build();
    }
}
