package ru.tpu.budget.dto

import java.time.LocalDate

data class PlanDto (
    val id: Int,
    val startDate: LocalDate,
    val accuracy: Double
) {
    val endDate: LocalDate = startDate.plusMonths(1)
}