package com.b5f1.atention.domain.plan.controller;

import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.dto.PlanResponseDto;
import com.b5f1.atention.domain.plan.service.PlanService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/plan")
@Api(tags = "일정")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlanResponseDto>> getAllUserPlans(@PathVariable UUID userId) {
        List<PlanResponseDto> plans = planService.getAllPlans(userId);
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PlanResponseDto>> getAllTeamPlans(@PathVariable Long teamId) {
        List<PlanResponseDto> plans = planService.getAllTeamPlans(teamId);
        return ResponseEntity.ok(plans);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<PlanResponseDto> createPlan(@PathVariable UUID userId, @RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto createdPlan = planService.createPlan(userId, planRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<PlanResponseDto> updatePlan(@PathVariable UUID userId, @PathVariable Long planId, @RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto updatedPlan = planService.updatePlan(userId, planId, planRequestDto);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable UUID userId, @PathVariable Long planId, @RequestBody PlanRequestDto planRequestDto) {
        planService.deletePlan(userId, planId, planRequestDto);
        return ResponseEntity.noContent().build();
    }
}
