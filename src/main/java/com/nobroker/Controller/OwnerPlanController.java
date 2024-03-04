package com.nobroker.Controller;

import com.nobroker.Entity.OwnerPlan;
import com.nobroker.Service.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner-plans")
public class OwnerPlanController {



    @Autowired
    private OwnerPlanService ownerPlanService;

    /*  http://localhost:8080/api/owner-plans/subscribe/1/120 */

    @PostMapping("/subscribe/{userId}/{duration}")
    public ResponseEntity<OwnerPlan> subscribeOwnerPlan(@PathVariable long userId , @PathVariable int duration){
        OwnerPlan ownerPlan = ownerPlanService.subscribeOwnerPlan(userId, duration);

        return new ResponseEntity<>(ownerPlan, HttpStatus.CREATED);

    }


}
