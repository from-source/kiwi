package io.fromsource.kiwi.core

import io.fromsource.kiwi.core.file.PathShould
import io.fromsource.kiwi.core.time.LocalDateShould
import java.nio.file.Path
import java.time.LocalDate

fun Path.should() = PathShould(this)

fun LocalDate.should() = LocalDateShould(this)