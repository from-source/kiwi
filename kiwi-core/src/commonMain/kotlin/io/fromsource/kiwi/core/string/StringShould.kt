package io.fromsource.kiwi.core.string

import io.fromsource.kiwi.core.BeAny
import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert

class StringShould(private val actual: String) :
        BeAny<String, StringShould>,
        BeEqual<String, StringShould>,
        BeComparable<String, StringShould> {

    override fun actual(): String = actual

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

    fun notBeBlank(): StringShould {
        val message = "'$actual' should not be blank"
        assert(actual.isNotBlank()) { message }
        return this
    }

    infix fun contain(expected: String): StringShould {
        val message = "'$actual' should contain '$expected'"
        assert(actual.contains(expected, false)) { message }
        return this
    }

    infix fun containIgnoringCase(expected: String): StringShould {
        val message = "'$actual' should contain ignoring case '$expected'"
        assert(actual.contains(expected, true)) { message }
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

    fun beDecapitalized(): StringShould {
        val message = "'$actual' should be decapitalised"
        assert(actual.decapitalize() == actual) { message }
        return this
    }

    infix fun match(regex: Regex): StringShould {
        val message = "'$actual' should match '$regex'"
        assert(actual.matches(regex)) { message }
        return this
    }

    fun beLowercase(): StringShould {
        val message = "'$actual' should be lowercase"
        assert(actual.toLowerCase() == actual) { message }
        return this
    }

    fun beUppercase(): StringShould {
        val message = "'$actual' should be uppercase"
        assert(actual.toUpperCase() == actual) { message }
        return this
    }

    fun beLong(): StringShould {
        val result = runCatching { actual.toLong() }
        val message = "'$actual' should be Long"
        assert(result.isSuccess) { message }
        return this
    }

    fun beInt(): StringShould {
        val result = runCatching { actual.toInt() }
        val message = "'$actual' should be Int"
        assert(result.isSuccess) { message }
        return this
    }

    fun beShort(): StringShould {
        val result = runCatching { actual.toShort() }
        val message = "'$actual' should be Short"
        assert(result.isSuccess) { message }
        return this
    }

    infix fun beEqualIgnoringCase(expected: String): StringShould {
        val message = "'$actual' should be equal ignoring case to '$expected'"
        assert(actual.equals(expected, true)) { message }
        return this
    }
}