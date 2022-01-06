package ru.tpu.budget

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

/**
 * Входная точка в приложение.
 */
@EnableJpaAuditing
@SpringBootApplication
class BudgetApplication

fun main(args: Array<String>) {
    runApplication<BudgetApplication>(*args)
}
