package com.b5f1.atention.domain.plan.service;

import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.dto.PlanResponseDto;
import com.b5f1.atention.domain.plan.repository.PlanRepository;
import com.b5f1.atention.entity.Plan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PlanServiceImpl implements PlanService{

    private final PlanRepository planRepository;

    /**
     * 모든 일정을 가져오는 메서드
     * @return List<Plan>
     */
    @Override
    public List<PlanResponseDto> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        return mapPlansToPlanResponseDtoList(plans);
    }

    /**
     * 유저 ID로 해당하는 유저의 일정을 가져오는 메서드
     * @param userId
     * @return List<Plan>
     */
    @Override
    public List<PlanResponseDto> getPlansByUserId(UUID userId) {
        List<Plan> plans = planRepository.findAllByUserIdAndIsDeletedFalse(userId);
        return mapPlansToPlanResponseDtoList(plans);
    }

    /**
     * 팀 ID로 해당하는 팀의 일정을 가져오는 메서드
     * @param teamId
     * @return List<Plan>
     */
    @Override
    public List<PlanResponseDto> getPlansByTeamId(Long teamId) {
        List<Plan> plans = planRepository.findAllByTeamIdAndIsDeletedFalse(teamId);
        return mapPlansToPlanResponseDtoList(plans);
    }

    /**
     * 일정 생성 후 DTO 로 변환하고 반환하는 메서드
     * @param  planRequestDto
     * @return savedPlan
     */
    @Override
    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        Plan plan = mapPlanRequestDtoToPlan(planRequestDto);
        Plan savedPlan = planRepository.save(plan);
        return mapPlanToPlanResponseDto(savedPlan);
    }

    /**
     * 일정 수정 후 DTO 로 변환하고 반환하는 메서드
     * @param planId
     * @param planRequestDto
     * @return updatedPlan
     */
    @Override
    public PlanResponseDto updatePlan(Long planId, PlanRequestDto planRequestDto) {
        Plan plan = planRepository.findByIdAndIsDeletedFalse(planId)
                .orElseThrow(() -> new RuntimeException("해당하는 일정이 없습니다."));
        Plan updatedPlan = planRepository.save(plan.updatePlan(planRequestDto));
//        mapPlanRequestDtoToPlan(planRequestDto);
//        Plan updatedPlan = planRepository.save(plan);
        return mapPlanToPlanResponseDto(updatedPlan);
    }

    /**
     * 일정 삭제하는 메서드
     * @param planId
     */
    @Override
    public void deletePlan(Long planId) {
        Optional<Plan> optionalPlan = planRepository.findByIdAndIsDeletedFalse(planId);
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            plan.deleted();
            planRepository.save(plan);
        } else {
            throw new RuntimeException("해당하는 일정이 없습니다");
        }
    }

    // 서비스 내부 로직

    /**
     * 일정 객체를 DTO 로 변환하는 메서드
     * @param plan
     * @return planResponseDto
     */
    private PlanResponseDto mapPlanToPlanResponseDto(Plan plan) {
        return PlanResponseDto.builder()
                .id(plan.getId())
                .name(plan.getName())
                .startTime(plan.getStartTime())
                .endTime(plan.getEndTime())
                .description(plan.getDescription())
                .build();
    }

    /**
     * DTO 를 일정 객체로 변환하는 메서드
     * @param planRequestDto
     * @return planResponseDto
     */
    private Plan mapPlanRequestDtoToPlan(PlanRequestDto planRequestDto) {
        return Plan.builder()
                .teamId(planRequestDto.getTeamId())
                .name(planRequestDto.getName())
                .startTime(planRequestDto.getStartTime())
                .endTime(planRequestDto.getEndTime())
                .description(planRequestDto.getDescription())
                .build();
    }

    /**
     * plans 를 DTO 로 변환하는 메서드
     * @param plans
     * @return planResponseDtoList
     */
    private List<PlanResponseDto> mapPlansToPlanResponseDtoList(List<Plan> plans) {
        return plans.stream()
                .map(this::mapPlanToPlanResponseDto)
                .collect(Collectors.toList());
    }

}
