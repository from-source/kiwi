package io.github.fromsource.kiwi.core

import io.github.fromsource.kiwi.core.collection.ListShould
import io.github.fromsource.kiwi.core.number.*
import io.github.fromsource.kiwi.core.string.StringShould

fun <T> List<T>.should() = ListShould(this)

fun Int.should() = IntShould(this)
fun Short.should() = ShortShould(this)
fun Long.should() = LongShould(this)
fun Float.should() = FloatShould(this)
fun Double.should() = DoubleShould(this)

fun String.should() = StringShould(this)
