package ru.tpu.budget.domain.entities

import ru.tpu.budget.domain.BaseAuditEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "planItems")
class PlanItem(
    @ManyToOne
    @JoinColumn(name = "plan_id")
    val plan: Plan,
    @ManyToOne
    @JoinColumn(name = "budgetItem_id")
    val budgetItem: BudgetItem,
    var value: Double,
) : BaseAuditEntity<Int>()
