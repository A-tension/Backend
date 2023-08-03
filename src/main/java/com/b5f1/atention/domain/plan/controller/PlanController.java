package com.b5f1.atention.domain.plan.controller;

import com.b5f1.atention.common.MessageWithData;
import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/plan")
@Api(tags = "일정")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/{userId}")
    @Operation(summary = "모든 일정 조회", description = "유저가 속한 모든 팀의 일정 조회 요청 API 입니다.")
    public ResponseEntity<MessageWithData> getAllUserPlans(Authentication authentication) {
//        return new ResponseEntity<>(new MessageWithData(("가입한 모든 그룹의 일정을 가져왔습니다."),planService.getAllPlans(authentication.getName()));
        return null;
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "팀 일정 조회", description = "팀의 일정 조회 요청 API 입니다.")
    public ResponseEntity<MessageWithData> getAllTeamPlans(@PathVariable Long teamId) {
//        return new ResponseEntity<>(new MessageWithData(("그룹의 일정을 가져왔습니다."),planService.getAllTeamPlans(teamId));
        return null;
    }

    @GetMapping("/{planId}")
    @Operation(summary = "일정 상세 조회", description = "일정 상세 조회 요청 API 입니다.")
    public ResponseEntity<MessageWithData> getPlan(@PathVariable Long planId) {
//        return new ResponseEntity<>(new MessageWithData(("일정 상세정보를 가져왔습니다."),planService.getPlan(planId));
        return null;
    }

    @PostMapping("/{userId}")
    @Operation(summary = "일정 생성", description = "일정 생성 요청 API 입니다.")
    public ResponseEntity<MessageWithData> createPlan(Authentication authentication, @RequestBody PlanRequestDto planRequestDto) {
//        return new ResponseEntity<>(new MessageWithData(("일정을 생성했습니다."),planService.createPlan(authentication.getName(), planRequestDto);
        return null;
    }

    @PutMapping("/{userId}/{planId}")
    @Operation(summary = "일정 수정", description = "일정 수정 요청 API 입니다.")
    public ResponseEntity<MessageWithData> updatePlan(Authentication authentication, @PathVariable Long planId, @RequestBody PlanRequestDto planRequestDto) {
//        return new ResponseEntity<>(new MessageWithData(("일정을 수정했습니다."),planService.updatePlan(authentication.getName(),planId), planRequestDto);
        return null;
    }

    @DeleteMapping("/{userId}/{planId}")
    @Operation(summary = "일정 삭제", description = "일정 삭제 요청 API 입니다.")
    public ResponseEntity<MessageWithData> deletePlan(Authentication authentication, @PathVariable Long planId, @RequestBody PlanRequestDto planRequestDto) {
//        return new ResponseEntity<>(new MessageWithData(("일정을 삭제했습니다."),planService.deletePlan(authentication.getName(),planId, planRequestDto));
        return null;
    }
}
