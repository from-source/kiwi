package io.github.fromsource.kiwi.core.number

class IntShould(private val actual: Int) {

    infix fun beEqual(expected: Int) {
        val message = "$actual should be == $expected"
        assert(actual == expected) { message }
    }

    infix fun beLessThan(expected: Int) {
        val message = "$actual should be < $expected"
        assert(actual < expected) { message }
    }

    infix fun beGreaterThan(expected: Int) {
        val message = "$actual should be > $expected"
        assert(actual > expected) { message }
    }
}