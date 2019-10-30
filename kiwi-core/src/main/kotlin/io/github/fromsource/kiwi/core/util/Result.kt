package io.github.fromsource.kiwi.core.util

import io.github.fromsource.kiwi.core.util.Result.Companion.failure
import io.github.fromsource.kiwi.core.util.Result.Companion.success

sealed class Result<T> {
    abstract fun isSuccess(): Boolean
    abstract fun getOrNull(): T?
    abstract fun throwableOrNull(): Throwable?
    fun isFailure(): Boolean = !isSuccess()

    companion object {
        fun <T> success(value: T) = Success(value)
        fun <T> failure(throwable: Throwable) = Failure<T>(throwable)
    }
}

class Success<T>(private val value: T) : Result<T>() {
    override fun throwableOrNull(): Throwable? = null
    override fun getOrNull(): T = value
    override fun isSuccess() = true
}

class Failure<T>(private val throwable: Throwable) : Result<T>() {
    override fun isSuccess(): Boolean = false
    override fun getOrNull(): T? = null
    override fun throwableOrNull(): Throwable = throwable
}

inline fun <R> runCatching(block: () -> R): Result<R> = try {
    success(block.invoke())
} catch (throwable: Throwable) {
    failure<R>(throwable)
}
