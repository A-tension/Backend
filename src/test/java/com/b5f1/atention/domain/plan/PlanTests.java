//package com.b5f1.atention.domain.plan;
//
//import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
//import com.b5f1.atention.domain.plan.dto.PlanResponseDto;
//import com.b5f1.atention.domain.plan.repository.PlanRepository;
//import com.b5f1.atention.domain.plan.service.PlanService;
//import com.b5f1.atention.entity.Plan;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//@Rollback
//public class PlanTests {
//
//    @Autowired
//    private PlanService planService;
//
//    @Autowired
//    private PlanRepository planRepository;
//
//    @Test
//    public void createPlanTest() {
//        // Given
//        PlanRequestDto planRequestDto = PlanRequestDto.builder()
//                .name("Test Plan")
//                .startTime(LocalDateTime.now())
//                .endTime(LocalDateTime.now().plusHours(2))
//                .description("Test Plan Description")
//                .build();
//
//        // When
//        PlanResponseDto createdPlan = planService.createPlan(UUID.randomUUID(), planRequestDto);
//
//        // Then
//        assertNotNull(createdPlan);
//        assertNotNull(createdPlan.getId());
//        assertEquals(planRequestDto.getName(), createdPlan.getName());
//        assertEquals(planRequestDto.getStartTime(), createdPlan.getStartTime());
//        assertEquals(planRequestDto.getEndTime(), createdPlan.getEndTime());
//        assertEquals(planRequestDto.getDescription(), createdPlan.getDescription());
//    }
//
//    @Test
//    public void updatePlanTest() {
//        // Given
//        Plan plan = createTestPlan();
//        PlanRequestDto planRequestDto = PlanRequestDto.builder()
//                .name("Updated Plan")
//                .startTime(LocalDateTime.now())
//                .endTime(LocalDateTime.now().plusHours(3))
//                .description("Updated Plan Description")
//                .build();
//
//        // When
//        PlanResponseDto updatedPlan = planService.updatePlan(UUID.randomUUID(), plan.getId(), planRequestDto);
//
//        // Then
//        assertNotNull(updatedPlan);
//        assertEquals(plan.getId(), updatedPlan.getId());
//        assertEquals(planRequestDto.getName(), updatedPlan.getName());
//        assertEquals(planRequestDto.getStartTime(), updatedPlan.getStartTime());
//        assertEquals(planRequestDto.getEndTime(), updatedPlan.getEndTime());
//        assertEquals(planRequestDto.getDescription(), updatedPlan.getDescription());
//    }
//
//    @Test
//    public void getPlansByTeamIdTest() {
//        // Given
//        Long teamId = 1L;
//        createTestPlanWithTeamId(teamId);
//
//        // When
//        List<PlanResponseDto> plans = planService.getAllTeamPlans(teamId);
//
//        // Then
//        assertFalse(plans.isEmpty());
//    }
//
//    private Plan createTestPlan() {
//        Plan plan = Plan.builder()
//                .name("Test Plan")
//                .startTime(LocalDateTime.now())
//                .endTime(LocalDateTime.now().plusHours(2))
//                .description("Test Plan Description")
//                .build();
//        return planRepository.save(plan);
//    }
//
//    private Plan createTestPlanWithTeamId(Long teamId) {
//        Plan plan = Plan.builder()
//                .teamId(teamId)
//                .name("Test Plan")
//                .startTime(LocalDateTime.now())
//                .endTime(LocalDateTime.now().plusHours(2))
//                .description("Test Plan Description")
//                .build();
//        return planRepository.save(plan);
//    }
//}
