package ru.tpu.budget

import org.springframework.data.crossstore.ChangeSetPersister
import ru.tpu.budget.domain.entities.BudgetItem
import ru.tpu.budget.domain.entities.BudgetOperation
import ru.tpu.budget.domain.repositories.BudgetItemRepository
import ru.tpu.budget.domain.repositories.BudgetOperationRepository
import ru.tpu.budget.domain.repositories.BudgetStateRepository
import ru.tpu.budget.domain.repositories.CategoryRepository
import ru.tpu.budget.dto.BudgetItemDto
import ru.tpu.budget.dto.BudgetOperationDto
import ru.tpu.budget.dto.BudgetStateDto
import ru.tpu.budget.dto.CategoryDto
import java.time.LocalDate


interface IBudgetService {
    fun getAllOperations(): List<BudgetOperationDto>
    fun getAllItems(): List<BudgetItemDto>
    fun createNewBudgetItem(title: String, categoryId: Int): BudgetItemDto
    fun createNewOperation(value: Double, budgetItemId: Int): BudgetOperationDto
    fun deleteBudgetItem(id: Int)
    fun deleteOperation(id: Int)
    fun getBudgetState(): BudgetStateDto
}

class BudgetService(
    private val budgetOperationRepository: BudgetOperationRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val categoryRepository: CategoryRepository,
    private val budgetStateRepository: BudgetStateRepository,
) : IBudgetService {
    override fun getAllOperations(): List<BudgetOperationDto> {
        val now = LocalDate.now()
        return budgetOperationRepository.findAllByDateBetweenOrderByDateDesc(now.minusMonths(1), now).map { operation ->
            BudgetOperationDto(
                operation.id!!,
                operation.date,
                CategoryDto(
                    operation.budgetItem.category.id!!,
                    operation.budgetItem.category.title,
                    operation.budgetItem.category.isIncome,
                ),
                operation.value,
                operation.budgetItem.title,
            )
        }
    }

    override fun getAllItems(): List<BudgetItemDto> {
        return budgetItemRepository.findByOrderByCategory_TitleAscTitleAsc().map { budgetItem -> BudgetItemDto(
            budgetItem.id!!,
            budgetItem.title,
            CategoryDto(
                budgetItem.category.id!!,
                budgetItem.category.title,
                budgetItem.category.isIncome,
            )
        ) }
    }

    override fun createNewBudgetItem(title: String, categoryId: Int): BudgetItemDto {
        val category = categoryRepository.getById(categoryId)
        val newBudgetItem = BudgetItem(title, category)

        val savedBudgetItemDto = budgetItemRepository.save(newBudgetItem)

        return BudgetItemDto(
            savedBudgetItemDto.id!!,
            savedBudgetItemDto.title,
            CategoryDto(
                savedBudgetItemDto.category.id!!,
                savedBudgetItemDto.category.title,
                savedBudgetItemDto.category.isIncome,
            )
        )
    }

    override fun createNewOperation(value: Double, budgetItemId: Int): BudgetOperationDto {
        val budgetItem = budgetItemRepository.findById(budgetItemId)
        if (!budgetItem.isPresent) throw ChangeSetPersister.NotFoundException()

        val newBudgetOperation = budgetOperationRepository.save(BudgetOperation(budgetItem.get(), value, LocalDate.now()))

        val category = newBudgetOperation.budgetItem.category

        var diffValue = newBudgetOperation.value
        if (!category.isIncome) {
            diffValue *= -1
        }
        changeBudgetState(diffValue)

        return BudgetOperationDto(
            newBudgetOperation.id!!,
            newBudgetOperation.date,
            CategoryDto(
                category.id!!,
                category.title,
                category.isIncome,
            ),
            newBudgetOperation.value,
            newBudgetOperation.budgetItem.title,
        )
    }

    override fun deleteBudgetItem(id: Int) {
        budgetItemRepository.deleteById(id)
    }

    override fun deleteOperation(id: Int) {
        val operationNullable = budgetOperationRepository.findById(id)

        if (!operationNullable.isPresent) {
            return
        }

        val operation = operationNullable.get()
        var value = operation.value

        // We invert condition sign because we add value inside changeBudgetState method
        // there should be !isIncome and '-' operation, but we have isIncome and '+' operation
        if (operation.budgetItem.category.isIncome) {
            value *= -1
        }
        changeBudgetState(value)

        budgetOperationRepository.delete(operation)
    }

    override fun getBudgetState(): BudgetStateDto {
        val budgetState = budgetStateRepository.getState()

        return BudgetStateDto(budgetState.id!!, budgetState.value)
    }

    private fun changeBudgetState(value: Double) {
        val budgetState = budgetStateRepository.getState()
        budgetState.value += value

        budgetStateRepository.save(budgetState)
    }
}





