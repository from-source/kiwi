package org.fromsource.kiwi.core.number

import org.fromsource.kiwi.core.BeAny
import org.fromsource.kiwi.core.BeComparable
import org.fromsource.kiwi.core.BeEqual
import java.math.BigInteger

class BigIntegerShould(private val actual: BigInteger) :
        BeAny<BigInteger, BigIntegerShould>,
        BeEqual<BigInteger, BigIntegerShould>,
        BeComparable<BigInteger, BigIntegerShould> {

    override fun actual(): BigInteger = actual

    fun bePositive() = beGreaterThan(BigInteger.ZERO)

    fun beNegative() = beLessThan(BigInteger.ZERO)

    fun beZero(): BigIntegerShould = beEqual(BigInteger.ZERO)
}