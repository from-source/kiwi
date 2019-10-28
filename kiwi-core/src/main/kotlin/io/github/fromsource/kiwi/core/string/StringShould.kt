package io.github.fromsource.kiwi.core.string

class StringShould(private val actual: String) {

    infix fun beEqual(expected: String): StringShould {
        val message = "'$actual' should == '$expected'"
        assert(actual == expected) { message }
        return this
    }

    fun beEmpty(): StringShould {
        val message = "'$actual' should be empty"
        assert(actual.isEmpty()) { message }
        return this
    }

    fun notBeEmpty(): StringShould {
        val message = "'$actual' should not be empty"
        assert(actual.isNotEmpty()) { message }
        return this
    }

    fun beBlank(): StringShould {
        val message = "'$actual' should be blank"
        assert(actual.isBlank()) { message }
        return this
    }

    infix fun contain(expected: String): StringShould {
        val message = "'$actual' should contain '$expected'"
        assert(actual.contains(expected, false)) { message }
        return this
    }
}