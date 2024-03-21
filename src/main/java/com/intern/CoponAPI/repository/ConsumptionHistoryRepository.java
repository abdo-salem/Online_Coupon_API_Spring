package com.intern.CoponAPI.repository;

import com.intern.CoponAPI.entity.ConsumptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionHistoryRepository extends JpaRepository<ConsumptionHistory, Long> {
}
