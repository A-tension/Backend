package com.b5f1.atention.domain.plan.controller;

import com.b5f1.atention.common.MessageWithData;
import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.dto.PlanResponseDto;
import com.b5f1.atention.domain.plan.service.PlanService;
import com.b5f1.atention.entity.Plan;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/plan")
@Api(tags = "일정")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/{userId}")
    public ResponseEntity<MessageWithData> getAllUserPlans(@PathVariable UUID userId) {
//        List<PlanResponseDto> plans = planService.getAllPlans(userId);
//        return ResponseEntity.ok(plans);
        return null;
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<MessageWithData> getAllTeamPlans(@PathVariable Long teamId) {
//        List<PlanResponseDto> plans = planService.getAllTeamPlans(teamId);
//        return ResponseEntity.ok(plans);
        return null;
    }

    @GetMapping("/{planId}")
    public ResponseEntity<MessageWithData> getPlan(@PathVariable Long planId) {
//        Optional<Plan> plan = planService.getPlan(planId);
//        return plan;
        return null;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<MessageWithData> createPlan(@PathVariable UUID userId, @RequestBody PlanRequestDto planRequestDto) {
//        PlanResponseDto createdPlan = planService.createPlan(userId, planRequestDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
        return null;
    }

    @PutMapping("/{userId}/{planId}")
    public ResponseEntity<MessageWithData> updatePlan(@PathVariable UUID userId, @PathVariable Long planId, @RequestBody PlanRequestDto planRequestDto) {
//        PlanResponseDto updatedPlan = planService.updatePlan(userId, planId, planRequestDto);
//        return ResponseEntity.ok(updatedPlan);
        return null;
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<MessageWithData> deletePlan(@PathVariable UUID userId, @PathVariable Long planId, @RequestBody PlanRequestDto planRequestDto) {
//        planService.deletePlan(userId, planId, planRequestDto);
//        return ResponseEntity.noContent().build();
        return null;
    }
}
