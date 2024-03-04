package com.nobroker.Service.Impl;

import com.nobroker.Entity.OwnerPlan;
import com.nobroker.Repository.OwnerPlanRepository;
import com.nobroker.Service.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class OwnerPlanServiceImpl implements OwnerPlanService {

    @Autowired
    private OwnerPlanRepository ownerPlanRepository;


    @Override
    public OwnerPlan subscribeOwnerPlan(long userId, int duration) {

        OwnerPlan ownerPlan = new OwnerPlan();
        ownerPlan.setUserId(userId);
        ownerPlan.setSubscriptionActive(true);
        ownerPlan.setSubscriptionActiveDate(LocalDate.now());
        ownerPlan.setSubscriptionExpireDate(LocalDate.now().plusDays(duration));
        ownerPlan.setNumberOfDays(duration);

        /*  save the all data .....*/
        OwnerPlan savedOwnerPlan = ownerPlanRepository.save(ownerPlan);

        return savedOwnerPlan;
    }
}
