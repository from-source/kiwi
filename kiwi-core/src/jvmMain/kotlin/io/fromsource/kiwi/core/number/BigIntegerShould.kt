package io.fromsource.kiwi.core.number

import java.math.BigInteger

class BigIntegerShould(private val actual: BigInteger) : NumberShould<BigInteger> {
    override fun actual(): BigInteger = actual

    override fun bePositive() = beGreaterThan(BigInteger.ZERO) as BigIntegerShould

    override fun beNegative() = beLessThan(BigInteger.ZERO) as BigIntegerShould

    override fun beZero(): BigIntegerShould = beEqual(BigInteger.ZERO) as BigIntegerShould
}