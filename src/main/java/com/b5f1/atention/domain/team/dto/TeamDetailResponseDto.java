package com.b5f1.atention.domain.team.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDetailResponseDto {

    // 팀 고유 식별값
    private Long teamId;
    // 팀 이름
    private String name;
    // 팀 프로필 사진 링크
    private String profileImage;
    // 팀 소개
    private String description;
    // 유저 프로필 관련 데이터 (userId, name, profileImage)
    private List<UserProfileDto> userProfileDtoList;

}
