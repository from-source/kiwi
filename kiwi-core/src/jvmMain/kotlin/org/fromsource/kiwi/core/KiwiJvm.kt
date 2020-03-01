package org.fromsource.kiwi.core

import org.fromsource.kiwi.core.file.PathShould
import org.fromsource.kiwi.core.number.BigDecimalShould
import org.fromsource.kiwi.core.number.BigIntegerShould
import org.fromsource.kiwi.core.time.LocalDateShould
import org.fromsource.kiwi.core.time.LocalDateTimeShould
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
