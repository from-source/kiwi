package org.fromsource.kiwi.core.number

import org.fromsource.kiwi.core.BeAny
import org.fromsource.kiwi.core.BeComparable
import org.fromsource.kiwi.core.BeEqual
import java.math.BigDecimal

class BigDecimalShould(private val actual: BigDecimal) :
        BeAny<BigDecimal, BigDecimalShould>,
        BeEqual<BigDecimal, BigDecimalShould>,
        BeComparable<BigDecimal, BigDecimalShould> {
    
    override fun actual(): BigDecimal = actual

    fun bePositive() = beGreaterThan(BigDecimal.ZERO)

    fun beNegative() = beLessThan(BigDecimal.ZERO)

    fun beZero(): BigDecimalShould = beEqual(BigDecimal.ZERO)
}