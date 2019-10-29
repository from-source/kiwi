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

    infix fun startWith(expected: String): StringShould {
        val message = "'$actual' should start with '$expected'"
        assert(actual.startsWith(expected, false)) { message }
        return this
    }

    infix fun endWith(expected: String): StringShould {
        val message = "'$actual' should end with '$expected'"
        assert(actual.endsWith(expected, false)) { message }
        return this
    }

    infix fun haveLength(expected: Int): StringShould {
        val message = "'$actual' should have length $expected"
        assert(actual.length == expected) { message }
        return this
    }

    fun beCapitalised(): StringShould {
        val message = "'$actual' should be capitalised"
        assert(actual.capitalize() == actual) { message }
        return this
    }
}