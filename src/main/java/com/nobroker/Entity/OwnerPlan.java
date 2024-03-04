package com.nobroker.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "owners_plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownersPlanId;

    @Column(name = "user_id",unique = true)
    private long userId;

    private boolean subscriptionActive;

    private LocalDate subscriptionActiveDate;

    private LocalDate subscriptionExpireDate;

    private int numberOfDays;



}
