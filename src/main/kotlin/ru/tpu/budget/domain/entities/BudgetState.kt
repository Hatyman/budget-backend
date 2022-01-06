package ru.tpu.budget.domain.entities

import io.swagger.v3.oas.annotations.media.Schema
import ru.tpu.budget.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

@Schema(
    description = "Состояние бюджета",
)
@Entity
@Table(name = "budgetState")
class BudgetState(
    var value: Double = 0.0,
): BaseEntity<Int>()
