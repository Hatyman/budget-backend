package ru.tpu.budget

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.tpu.budget.domain.repositories.*
import ru.tpu.budget.services.CategoryService
import ru.tpu.budget.services.ICategoryService
import ru.tpu.budget.services.IPlanService
import ru.tpu.budget.services.PlanService
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

/**
 * Конфигурации нужны для явного задания зависимостей в IoC контейнере
 */
@Configuration
class BudgetConfig {

    @Bean
    @Autowired
    fun budgetService(
        budgetOperationRepository: BudgetOperationRepository,
        budgetItemRepository: BudgetItemRepository,
        categoryRepository: CategoryRepository,
        budgetStateRepository: BudgetStateRepository
    ): IBudgetService {
        return BudgetService(budgetOperationRepository, budgetItemRepository, categoryRepository, budgetStateRepository)
    }

    @Bean
    @Autowired
    fun planService(
        planRepository: PlanRepository,
        planItemRepository: PlanItemRepository,
        budgetItemRepository: BudgetItemRepository
    ): IPlanService {
        return PlanService(planRepository, planItemRepository, budgetItemRepository)
    }

    @Bean
    @Autowired
    fun categoryService(
        categoryRepository: CategoryRepository
    ): ICategoryService {
        return CategoryService(categoryRepository)
    }

    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            // В документацию включаем только наши контроллеры
            .apis(RequestHandlerSelectors.basePackage("ru.tpu"))
            .build()
    }
}