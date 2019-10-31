package io.github.fromsource.kiwi.core.number

import io.github.fromsource.kiwi.core.BeComparable
import io.github.fromsource.kiwi.core.BeEqual

class IntShould(private val actual: Int) : BeEqual<Int, IntShould>, BeComparable<Int, IntShould> {
    override fun actual(): Int = actual
}

class LongShould(private val actual: Long) : BeEqual<Long, LongShould>, BeComparable<Long, LongShould> {
    override fun actual(): Long = actual
}

class DoubleShould(private val actual: Double) : BeEqual<Double, DoubleShould>, BeComparable<Double, DoubleShould> {
    override fun actual(): Double = actual
}

class FloatShould(private val actual: Float) : BeEqual<Float, FloatShould>, BeComparable<Float, FloatShould> {
    override fun actual(): Float = actual
}

class ShortShould(private val actual: Short) : BeEqual<Short, ShortShould>, BeComparable<Short, ShortShould> {
    override fun actual(): Short = actual
}