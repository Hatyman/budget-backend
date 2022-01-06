package ru.tpu.budget.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.tpu.budget.domain.entities.PlanItem

@Repository
interface PlanItemRepository: JpaRepository<PlanItem, Int> {
    fun findAllByPlan_Id(plan_id: Int): MutableList<PlanItem>
}