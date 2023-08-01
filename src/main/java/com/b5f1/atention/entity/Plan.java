package com.b5f1.atention.entity;

import com.b5f1.atention.domain.plan.dto.PlanRequestDto;
import com.b5f1.atention.domain.plan.service.PlanService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column
    private Long teamId;

    @Column
    private String name;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column
    private String description;

    public Plan updatePlan(PlanRequestDto planRequestDto) {
        this.name = planRequestDto.getName();
        this.startTime = planRequestDto.getStartTime();
        this.endTime = planRequestDto.getEndTime();
        this.description = planRequestDto.getDescription();
        return this;
    }

}
