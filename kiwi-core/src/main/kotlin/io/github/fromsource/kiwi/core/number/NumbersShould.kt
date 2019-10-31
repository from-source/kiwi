package io.github.fromsource.kiwi.core.number

import io.github.fromsource.kiwi.core.BeComparable
import io.github.fromsource.kiwi.core.BeEqual

interface NumbersShould<T : Comparable<T>> : BeEqual<T, NumbersShould<T>>, BeComparable<T, NumbersShould<T>>

class IntShould(private val actual: Int) : NumbersShould<Int> {
    override fun actual(): Int = actual
}

class LongShould(private val actual: Long) : NumbersShould<Long> {
    override fun actual(): Long = actual
}

class DoubleShould(private val actual: Double) : NumbersShould<Double> {
    override fun actual(): Double = actual
}

class FloatShould(private val actual: Float) : NumbersShould<Float> {
    override fun actual(): Float = actual
}

class ShortShould(private val actual: Short) : NumbersShould<Short> {
    override fun actual(): Short = actual
}