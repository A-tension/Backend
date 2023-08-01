package com.b5f1.atention.domain.plan.service;

import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.dto.PlanResponseDto;
import com.b5f1.atention.domain.plan.repository.PlanRepository;
import com.b5f1.atention.domain.team.repository.TeamParticipantRepository;
import com.b5f1.atention.domain.team.repository.TeamRepository;
import com.b5f1.atention.domain.user.repository.UserRepository;
import com.b5f1.atention.entity.Plan;
import com.b5f1.atention.entity.Team;
import com.b5f1.atention.entity.TeamParticipant;
import com.b5f1.atention.entity.User;
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
    private final TeamRepository teamRepository;
    private final TeamParticipantRepository teamParticipantRepository;
    private final UserRepository userRepository;

    /**
     * 팀 ID로 해당하는 팀의 일정을 가져오는 메서드
     * @param userId
     * @param teamId
     * @return List<Plan>
     */
    @Override
    public List<PlanResponseDto> getAllTeamPlans(UUID userId, Long teamId) {
        if (userId != null) {
            User user = userRepository.findByIdAndIsDeletedFalse(userId)
                    .orElseThrow(() -> new RuntimeException("해당 유저가 없습니다."));
            List<TeamParticipant> teamParticipantList = user.getTeamParticipantList();
            for (TeamParticipant teamParticipant : teamParticipantList) {
                teamParticipant.getTeam();
                teamParticipant.getHasAuthority();
            }
        }
        List<Plan> plans = planRepository.findAllByTeamIdAndIsDeletedFalse(teamId);
        return mapPlansToPlanResponseDtoList(plans);
    }

    /**
     * 일정 생성 후 DTO 로 변환하고 반환하는 메서드
     * @param userId
     * @param  planRequestDto
     * @return savedPlan
     */
    @Override
    public PlanResponseDto createPlan(UUID userId, PlanRequestDto planRequestDto) {
        Optional<User> currentUser = userRepository.findByIdAndIsDeletedFalse(userId);
        // 팀 ID로 팀 가져오기
        Optional<Team> currentTeam = teamRepository.findByIdAndIsDeletedFalse(planRequestDto.getTeamId());

        if (currentUser.isEmpty() || currentTeam.isEmpty()) {
            throw new RuntimeException("해당 사용자 또는 팀을 찾을 수 없습니다.");
        }

        Optional<TeamParticipant> optionalTeamParticipant =
                teamParticipantRepository.findByUserAndTeamAndIsDeletedFalse(currentUser.get(), currentTeam.get());

        if (optionalTeamParticipant.isEmpty() || !optionalTeamParticipant.get().getHasAuthority()) {
            throw new RuntimeException("일정을 생성할 권한이 없습니다.");
        }

        Plan plan = mapPlanRequestDtoToPlan(planRequestDto);
        Plan savedPlan = planRepository.save(plan);
        return mapPlanToPlanResponseDto(savedPlan);
    }


    /**
     * 일정 수정 후 DTO 로 변환하고 반환하는 메서드
     * @param userId
     * @param planId
     * @param planRequestDto
     * @return updatedPlan
     */
    @Override
    public PlanResponseDto updatePlan(UUID userId, Long planId, PlanRequestDto planRequestDto) {
        Optional<User> currentUser = userRepository.findByIdAndIsDeletedFalse(userId);
        // 팀 ID로 팀 가져오기
        Optional<Team> currentTeam = teamRepository.findByIdAndIsDeletedFalse(planRequestDto.getTeamId());

        if (currentUser.isEmpty() || currentTeam.isEmpty()) {
            throw new RuntimeException("해당 사용자 또는 팀을 찾을 수 없습니다.");
        }

        Optional<TeamParticipant> optionalTeamParticipant =
                teamParticipantRepository.findByUserAndTeamAndIsDeletedFalse(currentUser.get(), currentTeam.get());

        if (optionalTeamParticipant.isEmpty() || !optionalTeamParticipant.get().getHasAuthority()) {
            throw new RuntimeException("일정을 수정할 권한이 없습니다.");
        }

        Plan plan = planRepository.findByIdAndIsDeletedFalse(planId)
                .orElseThrow(() -> new RuntimeException("해당하는 일정이 없습니다."));
        Plan updatedPlan = planRepository.save(plan.updatePlan(planRequestDto));

        return mapPlanToPlanResponseDto(updatedPlan);
    }

    /**
     * 일정 삭제하는 메서드
     * @param userId
     * @param planId
     * @param planRequestDto
     */
    @Override
    public void deletePlan(UUID userId, Long planId, PlanRequestDto planRequestDto) {
        Optional<User> currentUser = userRepository.findByIdAndIsDeletedFalse(userId);
        // 팀 ID로 팀 가져오기
        Optional<Team> currentTeam = teamRepository.findByIdAndIsDeletedFalse(planRequestDto.getTeamId());

        if (currentUser.isEmpty() || currentTeam.isEmpty()) {
            throw new RuntimeException("해당 사용자 또는 팀을 찾을 수 없습니다.");
        }

        Optional<TeamParticipant> optionalTeamParticipant =
                teamParticipantRepository.findByUserAndTeamAndIsDeletedFalse(currentUser.get(), currentTeam.get());

        if (optionalTeamParticipant.isEmpty() || !optionalTeamParticipant.get().getHasAuthority()) {
            throw new RuntimeException("일정을 삭제할 권한이 없습니다.");
        }

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
