package io.fromsource.kiwi.core.bool

import io.fromsource.kiwi.core.BeEqual

class BooleanShould(private val actual: Boolean) :
    BeEqual<Boolean, BooleanShould> {

    override fun actual(): Boolean = actual

    fun beTrue(): BooleanShould = beEqual(true)

    fun beFalse(): BooleanShould = beEqual(false)

    infix fun be(expected: Boolean): BooleanShould = beEqual(expected)
}