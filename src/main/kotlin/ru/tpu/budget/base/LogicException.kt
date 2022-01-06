package ru.tpu.budget.base

class LogicException(
    val httpCode: Int,
    message: String
) : RuntimeException(message)