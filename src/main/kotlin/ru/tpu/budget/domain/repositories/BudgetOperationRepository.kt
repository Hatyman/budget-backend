package ru.tpu.budget.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.tpu.budget.domain.entities.BudgetOperation
import java.time.LocalDate

@Repository
interface BudgetOperationRepository : JpaRepository<BudgetOperation, Int> {
    fun findAllByDateBetweenOrderByDateDesc(startDate: LocalDate, endDate: LocalDate): MutableList<BudgetOperation>
    fun findAllByBudgetItem_Title(title: String): MutableList<BudgetOperation>
    fun findAllByBudgetItem_Category_Title(title: String): MutableList<BudgetOperation>
}