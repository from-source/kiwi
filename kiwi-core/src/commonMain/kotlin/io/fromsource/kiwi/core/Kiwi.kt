package io.fromsource.kiwi.core

import io.fromsource.kiwi.core.collection.ListShould
import io.fromsource.kiwi.core.collection.MapShould
import io.fromsource.kiwi.core.collection.SetShould
import io.fromsource.kiwi.core.number.*
import io.fromsource.kiwi.core.string.StringShould

fun <T> List<T>.should() = ListShould(this)
fun <T> Set<T>.should() = SetShould(this)
fun <K, V> Map<K, V>.should() = MapShould(this)

fun Any.should() = AnyShould(this)
fun Byte.should() = ByteShould(this)
fun Short.should() = ShortShould(this)
fun Int.should() = IntShould(this)
fun Long.should() = LongShould(this)
fun Float.should() = FloatShould(this)
fun Double.should() = DoubleShould(this)

/**
 * Temporary rename should() to _should() for unsigned types
 * https://youtrack.jetbrains.com/issue/KT-35305
 */
internal fun UByte._should() = UByteShould(this)
internal fun UShort._should() = UShortShould(this)
internal fun UInt._should() = UIntShould(this)
internal fun ULong._should() = ULongShould(this)

fun String.should() = StringShould(this)

fun <T> Result<T>.should(): ResultShould<T> = ResultShould(this)

fun <A, B> Pair<A, B>.should(): PairShould<A, B> = PairShould(this)

fun assert(condition: Boolean, lazyMessage: () -> String) {
    if(!condition) throw AssertionError(lazyMessage())
}