package ru.tpu.budget.dto

data class PlanItemDto(
    val id: Int,
    val title: String,
    val category: CategoryDto,
    val value: Double,
)