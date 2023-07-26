package com.b5f1.atention.domain.team.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {
    private Long groupId;
    private String name;
    private String profileImage;
}
