package ru.tpu.budget.controller

import org.springframework.web.bind.annotation.*
import ru.tpu.budget.base.Response
import ru.tpu.budget.dto.CategoryDto
import ru.tpu.budget.services.ICategoryService

@RestController
@RequestMapping("/category")
class CategoryController(
    private val categoryService: ICategoryService
) {
    @GetMapping("/")
    fun getAllCategories(): Response<List<CategoryDto>> {
        return Response(data = categoryService.getAllCategories())
    }

    @PostMapping("/")
    fun createCategory(title: String, isIncome: Boolean): Response<CategoryDto> {
        return Response(data = categoryService.createCategory(title, isIncome))
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Int) {
        categoryService.deleteCategory(id)
    }
}