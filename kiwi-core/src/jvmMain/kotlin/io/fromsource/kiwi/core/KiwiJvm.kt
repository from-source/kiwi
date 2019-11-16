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

fun Path.should() = PathShould(this)

fun LocalDate.should() = LocalDateShould(this)
fun LocalDateTime.should() = LocalDateTimeShould(this)

fun BigDecimal.should() = BigDecimalShould(this)
fun BigInteger.should() = BigIntegerShould(this)