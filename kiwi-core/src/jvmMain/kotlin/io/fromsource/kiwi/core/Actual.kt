package io.fromsource.kiwi.core

import kotlin.reflect.KClass

actual fun <T> Result<T>.hasException(throwable: KClass<out Throwable>): Boolean {
    return throwable.java == this.exceptionOrNull()?.javaClass
}