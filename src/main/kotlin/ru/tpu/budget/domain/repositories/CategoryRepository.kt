package ru.tpu.budget.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.tpu.budget.domain.entities.Category

@Repository
interface CategoryRepository : JpaRepository<Category, Int> {
    fun findByOrderByIsIncomeAscTitleAsc(): List<Category>
}