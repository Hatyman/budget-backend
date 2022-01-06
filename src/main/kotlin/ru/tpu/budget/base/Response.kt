package ru.tpu.budget.base

class Response<T>(
    val code: Int = 200,
    val data: T
)