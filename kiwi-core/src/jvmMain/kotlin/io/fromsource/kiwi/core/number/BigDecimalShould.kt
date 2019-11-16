package io.fromsource.kiwi.core.number

import java.math.BigDecimal

class BigDecimalShould(private val actual: BigDecimal) : NumberShould<BigDecimal> {
    override fun actual(): BigDecimal = actual

    override fun bePositive() = beGreaterThan(BigDecimal.ZERO)

    override fun beNegative() = beLessThan(BigDecimal.ZERO)
}