package io.github.fromsource.kiwi.core.string

class StringShould(private val actual: String) {

    infix fun beEqual(expected: String) {
        val message = "'$actual' should == '$expected'"
        assert(actual == expected) { message }
    }

    fun beEmpty() {
        val message = "'$actual' should be empty"
        assert(actual.isEmpty()) { message }
    }

    fun beBlank() {
        val message = "'$actual' should be blank"
        assert(actual.isBlank()) { message }
    }

    infix fun contain(expected: String) {
        val message = "'$actual' should contain '$expected'"
        assert(actual.contains(expected, false)) { message }
    }

}