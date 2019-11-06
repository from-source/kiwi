package io.github.fromsource.kiwi.core.number

import io.github.fromsource.kiwi.core.BeComparable
import io.github.fromsource.kiwi.core.BeEqual

interface NumberShould<T : Comparable<T>> : BeEqual<T, NumberShould<T>>, BeComparable<T, NumberShould<T>>

class ByteShould(private val actual: Byte) : NumberShould<Byte> {
    override fun actual(): Byte = actual

    infix fun beLessThan(expected: Short): NumberShould<Byte> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): NumberShould<Byte> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): NumberShould<Byte> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): NumberShould<Byte> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): NumberShould<Byte> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Short): NumberShould<Byte> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Int): NumberShould<Byte> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Long): NumberShould<Byte> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Float): NumberShould<Byte> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Double): NumberShould<Byte> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}

class ShortShould(private val actual: Short) : NumberShould<Short> {
    override fun actual(): Short = actual

    infix fun beLessThan(expected: Byte): NumberShould<Short> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): NumberShould<Short> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): NumberShould<Short> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): NumberShould<Short> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): NumberShould<Short> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Byte): NumberShould<Short> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Int): NumberShould<Short> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Long): NumberShould<Short> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Float): NumberShould<Short> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Double): NumberShould<Short> {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}

class IntShould(private val actual: Int) : NumberShould<Int> {
    override fun actual(): Int = actual

    infix fun beLessThan(expected: Byte): NumberShould<Int> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Short): NumberShould<Int> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): NumberShould<Int> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): NumberShould<Int> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): NumberShould<Int> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }
}

class LongShould(private val actual: Long) : NumberShould<Long> {
    override fun actual(): Long = actual

    infix fun beLessThan(expected: Byte): NumberShould<Long> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Short): NumberShould<Long> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): NumberShould<Long> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): NumberShould<Long> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): NumberShould<Long> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }
}

class DoubleShould(private val actual: Double) : NumberShould<Double> {
    override fun actual(): Double = actual

    infix fun beLessThan(expected: Byte): NumberShould<Double> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Short): NumberShould<Double> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): NumberShould<Double> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): NumberShould<Double> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): NumberShould<Double> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }
}

class FloatShould(private val actual: Float) : NumberShould<Float> {
    override fun actual(): Float = actual

    infix fun beLessThan(expected: Byte): NumberShould<Float> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Short): NumberShould<Float> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): NumberShould<Float> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): NumberShould<Float> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): NumberShould<Float> {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }
}
