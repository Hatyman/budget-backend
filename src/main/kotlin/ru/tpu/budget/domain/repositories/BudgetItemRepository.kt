package ru.tpu.budget.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.tpu.budget.domain.entities.BudgetItem

@Repository
interface BudgetItemRepository: JpaRepository<BudgetItem, Int> {
    fun findByOrderByCategory_TitleAscTitleAsc(): List<BudgetItem>
}