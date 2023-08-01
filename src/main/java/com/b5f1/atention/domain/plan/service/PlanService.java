package com.b5f1.atention.domain.plan.service;

import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.dto.PlanResponseDto;

import java.util.List;
import java.util.UUID;

public interface PlanService {

    // 팀 ID로 일정 가져오기
    List<PlanResponseDto> getAllTeamPlans(UUID userId, Long teamId);
    // 일정 생성
    PlanResponseDto createPlan(UUID userId, PlanRequestDto planRequestDto);
    // 일정 수정
    PlanResponseDto updatePlan(UUID userId, Long planId, PlanRequestDto planRequestDto);
    // 일정 삭제
    void deletePlan(UUID userId, Long planId, PlanRequestDto planRequestDto);

}
