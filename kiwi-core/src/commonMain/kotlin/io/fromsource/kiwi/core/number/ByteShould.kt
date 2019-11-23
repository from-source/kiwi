package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.assert

class ByteShould(private val actual: Byte) : NumberShould<Byte> {
    override fun actual(): Byte = actual

    override fun bePositive(): ByteShould = beGreaterThan(0)

    override fun beNegative(): ByteShould = beLessThan(0)

    override fun beZero(): ByteShould  = beEqual(0.toByte()) as ByteShould

    infix fun beLessThan(expected: Short): ByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): ByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): ByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): ByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): ByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Short): ByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Int): ByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Long): ByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Float): ByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Double): ByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}