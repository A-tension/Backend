package com.b5f1.atention.domain.plan.service;

import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.dto.PlanResponseDto;
import com.b5f1.atention.entity.Plan;

import java.util.List;
import java.util.UUID;

public interface PlanService {

    // 모든 일정 가져오기
    List<PlanResponseDto> getAllPlans();
    // 유저 ID로 일정 가져오기
    List<PlanResponseDto> getPlansByUserId(UUID userId);
    // 팀 ID로 일정 가져오기
    List<PlanResponseDto> getPlansByTeamId(Long teamId);
    // 일정 생성
    PlanResponseDto createPlan(PlanRequestDto planRequestDto);
    // 일정 수정
    PlanResponseDto updatePlan(Long planId, PlanRequestDto planRequestDto);
    // 일정 삭제
    void deletePlan(Long planId);

}
