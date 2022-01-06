package ru.tpu.budget.services

import ru.tpu.budget.domain.entities.Plan
import ru.tpu.budget.domain.entities.PlanItem
import ru.tpu.budget.domain.repositories.BudgetItemRepository
import ru.tpu.budget.domain.repositories.PlanItemRepository
import ru.tpu.budget.domain.repositories.PlanRepository
import ru.tpu.budget.dto.CategoryDto
import ru.tpu.budget.dto.PlanDto
import ru.tpu.budget.dto.PlanItemDto
import java.time.LocalDate

interface IPlanService {
    fun getAllPlans(): List<PlanDto>
    fun createNewPlan(startDate: LocalDate): PlanDto
    fun getPlanItems(planId: Int): List<PlanItemDto>
    fun createNewPlanItem(value: Double, planId: Int, budgetItemId: Int): PlanItemDto
    fun deletePlanItem(id: Int)
    fun deletePlan(id: Int)
}

class PlanService(
    private val planRepository: PlanRepository,
    private val planItemRepository: PlanItemRepository,
    private val budgetItemRepository: BudgetItemRepository,
) : IPlanService {
    override fun getAllPlans(): List<PlanDto> {
        return planRepository.findAllByOrderByStartDateDesc()
            .map { plan -> PlanDto(plan.id!!, plan.startDate, plan.accuracy) };
    }

    override fun createNewPlan(startDate: LocalDate): PlanDto {
        val newPlan = planRepository.save(Plan(startDate, 0.0))

        return PlanDto(newPlan.id!!, newPlan.startDate, newPlan.accuracy)
    }

    override fun deletePlan(id: Int) {
        planRepository.deleteById(id)
    }

    override fun getPlanItems(planId: Int): List<PlanItemDto> {
        return planItemRepository.findAllByPlan_Id(planId).map { planItem ->
            PlanItemDto(
                planItem.id!!,
                planItem.budgetItem.title,
                CategoryDto(
                    planItem.budgetItem.category.id!!,
                    planItem.budgetItem.category.title,
                    planItem.budgetItem.category.isIncome,
                ),
                planItem.value
            )
        }
    }

    override fun createNewPlanItem(value: Double, planId: Int, budgetItemId: Int): PlanItemDto {
        val budgetItem = budgetItemRepository.getById(budgetItemId)
        val plan = planRepository.getById(planId)

        val planItem = planItemRepository.save(PlanItem(plan, budgetItem, value))

        val category = planItem.budgetItem.category
        return PlanItemDto(
            planItem.id!!,
            planItem.budgetItem.title,
            CategoryDto(
                category.id!!,
                category.title,
                category.isIncome,
            ),
            planItem.value,
        )
    }

    override fun deletePlanItem(id: Int) {
        planItemRepository.deleteById(id)
    }
}