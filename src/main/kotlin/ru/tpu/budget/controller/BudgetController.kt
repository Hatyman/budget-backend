package ru.tpu.budget.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*
import ru.tpu.budget.IBudgetService
import ru.tpu.budget.base.Response
import ru.tpu.budget.dto.BudgetItemDto
import ru.tpu.budget.dto.BudgetOperationDto
import ru.tpu.budget.dto.BudgetStateDto

/**
 * Контроллер с эндпоинтами для ресурса /todo
 */
@RestController
@RequestMapping("/budget")
class BudgetController(
    private val budgetService: IBudgetService,
) {

    @GetMapping("/operations")
    @Operation(
        summary = "Вернуть список всех трат/пополнений"
    )
    fun getAllOperations(): Response<List<BudgetOperationDto>> {
        return Response(data = budgetService.getAllOperations())
    }

    @GetMapping("/items")
    fun getAllItems(): Response<List<BudgetItemDto>> {
        return Response(data = budgetService.getAllItems())
    }

    @PostMapping("/items")
    fun createNewItem(title: String, categoryId: Int): Response<BudgetItemDto> {
        return Response(data = budgetService.createNewBudgetItem(title, categoryId))
    }

    @PostMapping("/operations")
    fun createNewOperation(value: Double, budgetItemId: Int): Response<BudgetOperationDto> {
        return Response(data = budgetService.createNewOperation(value, budgetItemId))
    }

    @DeleteMapping("/operations/{id}")
    fun deleteOperation(@PathVariable id: Int) {
        budgetService.deleteOperation(id)
    }

    @DeleteMapping("/items/{id}")
    fun deleteItem(@PathVariable id: Int) {
        budgetService.deleteBudgetItem(id)
    }

    @GetMapping("/")
    fun getState(): Response<BudgetStateDto> {
        return Response(data = budgetService.getBudgetState())
    }
}