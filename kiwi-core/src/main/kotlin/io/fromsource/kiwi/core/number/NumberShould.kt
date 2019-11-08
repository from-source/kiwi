package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual

interface NumberShould<T : Comparable<T>> : io.fromsource.kiwi.core.BeEqual<T, NumberShould<T>>, io.fromsource.kiwi.core.BeComparable<T, NumberShould<T>> {

    fun bePositive(): NumberShould<T>
    fun beNegative(): NumberShould<T>
}
