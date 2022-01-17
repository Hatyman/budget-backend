package ru.tpu.budget.dto

data class CreatePlanItemRequestDto(
    val value: Double,
    val budgetItemId: Int
)
