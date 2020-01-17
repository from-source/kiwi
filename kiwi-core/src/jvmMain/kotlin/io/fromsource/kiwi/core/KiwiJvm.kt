package io.fromsource.kiwi.core

import io.fromsource.kiwi.core.file.PathShould
import io.fromsource.kiwi.core.number.BigDecimalShould
import io.fromsource.kiwi.core.number.BigIntegerShould
import io.fromsource.kiwi.core.time.LocalDateShould
import io.fromsource.kiwi.core.time.LocalDateTimeShould
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime

val Path.should get() = PathShould(this)

val LocalDate.should get() = LocalDateShould(this)
val LocalDateTime.should get() = LocalDateTimeShould(this)

val BigDecimal.should get() = BigDecimalShould(this)
val BigInteger.should get() = BigIntegerShould(this)
