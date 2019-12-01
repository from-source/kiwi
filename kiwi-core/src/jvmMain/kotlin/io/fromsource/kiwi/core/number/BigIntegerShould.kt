package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import java.math.BigInteger

class BigIntegerShould(private val actual: BigInteger) : BeEqual<BigInteger, BigIntegerShould>, BeComparable<BigInteger, BigIntegerShould> {
    override fun actual(): BigInteger = actual

    fun bePositive() = beGreaterThan(BigInteger.ZERO)

    fun beNegative() = beLessThan(BigInteger.ZERO)

    fun beZero(): BigIntegerShould = beEqual(BigInteger.ZERO)
}