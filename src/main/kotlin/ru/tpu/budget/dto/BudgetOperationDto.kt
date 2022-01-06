package ru.tpu.budget.dto

import java.time.LocalDate

data class BudgetOperationDto(
    val id: Int,
    val date: LocalDate,
    val category: CategoryDto,
    val value: Double,
    val title: String
)
