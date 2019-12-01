package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import java.math.BigDecimal

class BigDecimalShould(private val actual: BigDecimal) : BeEqual<BigDecimal, BigDecimalShould>, BeComparable<BigDecimal, BigDecimalShould> {
    override fun actual(): BigDecimal = actual

    fun bePositive() = beGreaterThan(BigDecimal.ZERO)

    fun beNegative() = beLessThan(BigDecimal.ZERO)

    fun beZero(): BigDecimalShould = beEqual(BigDecimal.ZERO)
}