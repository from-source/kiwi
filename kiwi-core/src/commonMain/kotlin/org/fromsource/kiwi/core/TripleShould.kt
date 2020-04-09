package org.fromsource.kiwi.core

class TripleShould<A, B, C>(val actual: Triple<A, B, C>) : BeAny<Triple <A, B, C>, TripleShould<A, B, C>> {

    override fun actual(): Triple<A, B, C> = actual

    fun haveFirstEqualTo(expected: A): TripleShould<A, B, C> {
        val message = "$actual should have first element equal to $expected"
        assert(actual.first == expected) { message }
        return this
    }

    fun haveSecondEqualTo(expected: B): TripleShould<A, B, C> {
        val message = "$actual should have second element equal to $expected"
        assert(actual.second == expected) { message }
        return this
    }

    fun haveThirdEqualTo(expected: C): TripleShould<A, B, C> {
        val message = "$actual should have third element equal to $expected"
        assert(actual.third == expected) { message }
        return this
    }
}