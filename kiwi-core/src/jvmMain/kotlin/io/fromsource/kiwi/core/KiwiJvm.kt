package io.fromsource.kiwi.core

import io.fromsource.kiwi.core.file.PathShould
import io.fromsource.kiwi.core.time.LocalDateShould
import io.fromsource.kiwi.core.time.LocalDateTimeShould
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime

fun Path.should() = PathShould(this)

fun LocalDate.should() = LocalDateShould(this)
fun LocalDateTime.should() = LocalDateTimeShould(this)