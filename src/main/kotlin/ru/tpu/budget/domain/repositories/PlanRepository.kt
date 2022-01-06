package ru.tpu.budget.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.tpu.budget.domain.entities.Plan

@Repository
interface PlanRepository : JpaRepository<Plan, Int> {
    fun findAllByOrderByStartDateDesc(): MutableList<Plan>;
}