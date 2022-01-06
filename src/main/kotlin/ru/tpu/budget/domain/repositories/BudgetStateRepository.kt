package ru.tpu.budget.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.tpu.budget.domain.entities.BudgetState

interface BudgetStateRepository: JpaRepository<BudgetState, Int> {
    @Query("select b from BudgetState b where b.id = 1")
    fun getState(): BudgetState
}