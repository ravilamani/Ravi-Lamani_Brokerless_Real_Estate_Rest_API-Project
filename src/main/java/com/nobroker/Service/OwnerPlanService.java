package com.nobroker.Service;

import com.nobroker.Entity.OwnerPlan;

public interface OwnerPlanService {


    OwnerPlan subscribeOwnerPlan(long userId, int duration);
}
