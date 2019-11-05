package io.github.fromsource.kiwi.core

import io.github.fromsource.kiwi.core.collection.ListShould
import io.github.fromsource.kiwi.core.collection.MapShould
import io.github.fromsource.kiwi.core.collection.SetShould
import io.github.fromsource.kiwi.core.file.PathShould
import io.github.fromsource.kiwi.core.number.*
import io.github.fromsource.kiwi.core.string.StringShould
import java.nio.file.Path

fun <T> List<T>.should() = ListShould(this)
fun <T> Set<T>.should() = SetShould(this)
fun <K, V> Map<K, V>.should() = MapShould(this)

fun Byte.should() = ByteShould(this)
fun Short.should() = ShortShould(this)
fun Int.should() = IntShould(this)
fun Long.should() = LongShould(this)
fun Float.should() = FloatShould(this)
fun Double.should() = DoubleShould(this)

fun String.should() = StringShould(this)

fun Path.should() = PathShould(this)