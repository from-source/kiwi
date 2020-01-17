package io.fromsource.kiwi.core

import io.fromsource.kiwi.core.bool.BooleanShould
import io.fromsource.kiwi.core.collection.ListShould
import io.fromsource.kiwi.core.collection.MapShould
import io.fromsource.kiwi.core.collection.SetShould
import io.fromsource.kiwi.core.number.ByteShould
import io.fromsource.kiwi.core.number.DoubleShould
import io.fromsource.kiwi.core.number.FloatShould
import io.fromsource.kiwi.core.number.IntShould
import io.fromsource.kiwi.core.number.LongShould
import io.fromsource.kiwi.core.number.ShortShould
import io.fromsource.kiwi.core.number.UByteShould
import io.fromsource.kiwi.core.number.UIntShould
import io.fromsource.kiwi.core.number.ULongShould
import io.fromsource.kiwi.core.number.UShortShould
import io.fromsource.kiwi.core.range.IntRangeShould
import io.fromsource.kiwi.core.string.StringShould

val <T> List<T>.should get() = ListShould(this)
val <T> Set<T>.should get() = SetShould(this)
val <K, V> Map<K, V>.should get() = MapShould(this)

val Any.should get() = AnyShould(this)
val Byte.should get() = ByteShould(this)
val Short.should get() = ShortShould(this)
val Int.should get() = IntShould(this)
val Long.should get() = LongShould(this)
val Float.should get() = FloatShould(this)
val Double.should get() = DoubleShould(this)
val Boolean.should get() = BooleanShould(this)
val String.should get()= StringShould(this)
val <T> Result<T>.should: ResultShould<T> get()= ResultShould(this)
val <A, B> Pair<A, B>.should: PairShould<A, B> get()= PairShould(this)

val IntRange.should: IntRangeShould
    get() = IntRangeShould(this)

/**
 * Temporary rename should to _should for unsigned types
 * https://youtrack.jetbrains.com/issue/KT-35305
 */
internal val UByte._should get() = UByteShould(this)
internal val UShort._should get() = UShortShould(this)
internal val UInt._should get() = UIntShould(this)
internal val ULong._should get() = ULongShould(this)

fun assert(condition: Boolean, lazyMessage: () -> String) {
    if(!condition) throw AssertionError(lazyMessage())
}