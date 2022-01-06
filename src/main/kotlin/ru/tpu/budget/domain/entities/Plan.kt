package ru.tpu.budget.domain.entities

import ru.tpu.budget.domain.BaseAuditEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "plans")
class Plan(
    val startDate: LocalDate,
    var accuracy: Double,

    @OneToMany(
        mappedBy = "plan",
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        cascade = [CascadeType.ALL]
    )
    val items: MutableList<PlanItem> = mutableListOf()
): BaseAuditEntity<Int>() {
    fun addItem(block: Plan.() -> PlanItem) {
        items.add(block())
    }
    fun setItems(block: Plan.() -> MutableSet<PlanItem>) {
        items.clear()
        items.addAll(block())
    }
}