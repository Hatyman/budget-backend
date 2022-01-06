package ru.tpu.budget.domain.entities

import io.swagger.v3.oas.annotations.media.Schema
import ru.tpu.budget.domain.BaseAuditEntity
import javax.persistence.*

@Schema(
    description = "Позиция изменения бюджета"
)
@Entity
@Table(name = "budgetItem")
class BudgetItem(
    var title: String,
    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "category_id")
    val category: Category,
): BaseAuditEntity<Int>()
