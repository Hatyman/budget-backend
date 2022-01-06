package ru.tpu.budget.domain.entities

import io.swagger.v3.oas.annotations.media.Schema
import ru.tpu.budget.domain.BaseEntity
import javax.persistence.*

@Schema(
    description = "Категория трат"
)
@Entity
@Table(name = "categories")
class Category (
    val title: String,
    val isIncome: Boolean,

    @OneToMany(
        mappedBy = "category",
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        cascade = [CascadeType.ALL]
    )
    val budgetItems: MutableList<BudgetItem> = mutableListOf()
): BaseEntity<Int>()