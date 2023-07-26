package com.b5f1.atention.domain.plan.repository;

import com.b5f1.atention.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    // userId로 해당 유저의 모든 일정 가져오기
    List<Plan> findAllByUserId(UUID userId);

    // teamId로 해당 팀의 모든 일정 가져오기
    List<Plan> findAllByTeamId(Long teamId);

}
