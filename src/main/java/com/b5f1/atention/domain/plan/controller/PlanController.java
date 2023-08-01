package com.b5f1.atention.domain.plan.controller;

import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.dto.PlanResponseDto;
import com.b5f1.atention.domain.plan.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/plan")
@Api(tags = "일정")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping
    @Operation(summary = "개인 일정 조회", description = "개인 일정 조회 요청 API 입니다.")
    public ResponseEntity<List<PlanResponseDto>> getAllPlans() {
        List<PlanResponseDto> plans = planService.getAllPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "팀 일정 조회", description = "팀 일정 조회 요청 API 입니다.")
    public ResponseEntity<List<PlanResponseDto>> getPlansByTeamId(@PathVariable Long teamId) {
        List<PlanResponseDto> plans = planService.getPlansByTeamId(teamId);
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "일정 생성", description = "일정 생성 요청 API 입니다.")
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto createdPlan = planService.createPlan(planRequestDto);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{planId}")
    @Operation(summary = "일정 수정", description = "일정 수정 요청 API입니다.")
    public ResponseEntity<PlanResponseDto> updatePlan(
            @PathVariable Long planId,
            @RequestBody PlanRequestDto planRequestDto
    ) {
        PlanResponseDto updatedPlan = planService.updatePlan(planId, planRequestDto);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    @DeleteMapping("/{planId}")
    @Operation(summary = "일정 삭제", description = "일정 삭제 요청 API입니다.")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
