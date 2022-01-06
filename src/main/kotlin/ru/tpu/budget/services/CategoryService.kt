package ru.tpu.budget.services

import ru.tpu.budget.domain.entities.Category
import ru.tpu.budget.domain.repositories.CategoryRepository
import ru.tpu.budget.dto.CategoryDto

interface ICategoryService {
    fun getAllCategories(): List<CategoryDto>
    fun createCategory(title: String, isIncome: Boolean): CategoryDto
    fun deleteCategory(id: Int)
}

class CategoryService(
    private val categoryRepository: CategoryRepository
): ICategoryService {
    override fun getAllCategories(): List<CategoryDto> {
        return categoryRepository.findByOrderByIsIncomeAscTitleAsc().map { category -> CategoryDto(
            category.id!!,
            category.title,
            category.isIncome,
        ) }
    }

    override fun createCategory(title: String, isIncome: Boolean): CategoryDto {
        val category = categoryRepository.save(Category(title, isIncome))

        return CategoryDto(
            category.id!!,
            category.title,
            category.isIncome,
        )
    }

    override fun deleteCategory(id: Int) {
        categoryRepository.deleteById(id)
    }

}