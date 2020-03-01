package org.fromsource.kiwi.core.bool

import org.fromsource.kiwi.core.BeEqual

class BooleanShould(private val actual: Boolean) :
    BeEqual<Boolean, BooleanShould> {

    override fun actual(): Boolean = actual

    fun beTrue(): BooleanShould = beEqual(true)

    fun beFalse(): BooleanShould = beEqual(false)
}