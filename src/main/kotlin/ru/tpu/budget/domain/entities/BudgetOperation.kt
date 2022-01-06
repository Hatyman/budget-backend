package ru.tpu.budget.domain.entities

import ru.tpu.budget.domain.BaseAuditEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "budgetOperation")
class BudgetOperation (
    @ManyToOne(
        fetch = FetchType.EAGER,
    )
    @JoinColumn(name = "budgetItem_id")
    val budgetItem: BudgetItem,
    var value: Double,
    val date: LocalDate,
): BaseAuditEntity<Int>()