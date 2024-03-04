package com.nobroker.Repository;

import com.nobroker.Entity.OwnerPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OwnerPlanRepository extends JpaRepository<OwnerPlan,Long> {


    List<OwnerPlan> findBySubscriptionExpireDateBeforeAndSubscriptionActiveTrue(LocalDate now);
}
