package io.fromsource.kiwi.core.time

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import java.time.LocalDateTime

class LocalDateTimeShould(private val actual: LocalDateTime): BeEqual<LocalDateTime, LocalDateTimeShould>, BeComparable<LocalDateTime, LocalDateTimeShould> {
    override fun actual() = actual

    infix fun beAfter(expected: LocalDateTime) = beGreaterThan(expected)

    infix fun beBefore(expected: LocalDateTime) = beLessThan(expected)
}