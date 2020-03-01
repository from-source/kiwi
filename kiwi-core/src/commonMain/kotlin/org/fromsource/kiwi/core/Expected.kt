package org.fromsource.kiwi.core

import kotlin.reflect.KClass

internal expect fun <T> Result<T>.hasException(throwable: KClass<out Throwable>): Boolean