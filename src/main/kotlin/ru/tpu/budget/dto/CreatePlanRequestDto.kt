package ru.tpu.budget.dto

import java.time.LocalDate

data class CreatePlanRequestDto(
    val startDate: LocalDate
)