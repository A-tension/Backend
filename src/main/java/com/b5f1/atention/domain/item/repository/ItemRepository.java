package com.b5f1.atention.domain.item.repository;

import com.b5f1.atention.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Team, Long> {
    //

}