package io.fromsource.kiwi.core.time

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import java.time.LocalDate

class LocalDateShould(private val actual: LocalDate): BeEqual<LocalDate, LocalDateShould>, BeComparable<LocalDate, LocalDateShould> {
    override fun actual() = actual

    infix fun beAfter(expected: LocalDate) = beGreaterThan(expected)

    infix fun beBefore(expected: LocalDate) = beLessThan(expected)
}