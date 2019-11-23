package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual

interface NumberShould<T : Comparable<T>> : BeEqual<T, NumberShould<T>>, BeComparable<T, NumberShould<T>> {

    fun bePositive(): NumberShould<T>
    fun beNegative(): NumberShould<T>
    fun beZero(): NumberShould<T>
}
