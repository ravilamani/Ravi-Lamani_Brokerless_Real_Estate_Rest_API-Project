package com.nobroker.Scheduler;



import com.nobroker.Entity.OwnerPlan;
import com.nobroker.Repository.OwnerPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SubscriptionExpirationScheduler {

    @Autowired
    private OwnerPlanRepository ownerPlanRepository;

    // Run the scheduler every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkSubscriptionExpiration() {
        List<OwnerPlan> activePlans = ownerPlanRepository.findBySubscriptionExpireDateBeforeAndSubscriptionActiveTrue(LocalDate.now());

        for (OwnerPlan plan : activePlans) {
            // Perform actions for expired subscriptions (e.g., send notifications, deactivate, etc.)
            plan.setSubscriptionActive(false);
            ownerPlanRepository.save(plan);
        }
    }
}

