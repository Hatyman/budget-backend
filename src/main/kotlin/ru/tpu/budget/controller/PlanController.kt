package ru.tpu.budget.controller

import org.springframework.web.bind.annotation.*
import ru.tpu.budget.base.Response
import ru.tpu.budget.dto.CreatePlanItemRequestDto
import ru.tpu.budget.dto.CreatePlanRequestDto
import ru.tpu.budget.dto.PlanDto
import ru.tpu.budget.dto.PlanItemDto
import ru.tpu.budget.services.IPlanService

@RestController
@RequestMapping("/plan")
class PlanController(
    private val planService: IPlanService
) {
    @GetMapping("/")
    fun getAllPlans(): Response<List<PlanDto>> {
        return Response(data = planService.getAllPlans())
    }

    @PostMapping("/")
    fun createNewPlan(@RequestBody dto: CreatePlanRequestDto): Response<PlanDto> {
        return Response(data = planService.createNewPlan(dto.startDate))
    }

    @DeleteMapping("/{id}")
    fun deletePlan(@PathVariable id: Int) {
        planService.deletePlan(id)
    }

    @GetMapping("/{planId}/items")
    fun getAllPlanItems(@PathVariable planId: Int): Response<List<PlanItemDto>> {
        return Response(data = planService.getPlanItems(planId))
    }

    @PostMapping("/{planId}/items")
    fun createNewPlanItem(dto: CreatePlanItemRequestDto, @PathVariable planId: Int): Response<PlanItemDto> {
        return Response(data = planService.createNewPlanItem(dto.value, planId, dto.budgetItemId))
    }

    @DeleteMapping("/items/{id}")
    fun deletePlanItem(@PathVariable id: Int) {
        planService.deletePlanItem(id)
    }
}