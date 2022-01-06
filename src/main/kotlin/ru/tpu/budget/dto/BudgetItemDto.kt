package ru.tpu.budget.dto

data class BudgetItemDto(
    val id: Int,
    val title: String,
    val category: CategoryDto,
)
