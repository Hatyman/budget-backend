package ru.tpu.budget.dto

data class CreateBudgetItemRequestDto(
    val title: String,
    val categoryId: Int,
)
