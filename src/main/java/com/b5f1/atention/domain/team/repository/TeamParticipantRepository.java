package com.b5f1.atention.domain.team.repository;

import com.b5f1.atention.entity.TeamParticipant;
import com.b5f1.atention.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamParticipantRepository extends JpaRepository<TeamParticipant, Long> {

    Optional<TeamParticipant> findByUser(User user);

    Optional<TeamParticipant> findByUserAndIsDeletedFalse(User user);
}