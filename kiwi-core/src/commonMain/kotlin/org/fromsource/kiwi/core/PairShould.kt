package org.fromsource.kiwi.core

class PairShould<A, B>(val actual: Pair<A, B>) :
        BeAny<Pair<A, B>, PairShould<A, B>> {

    override fun actual(): Pair<A, B> = actual

    fun haveFirstEqualTo(expected: A): PairShould<A, B> {
        val message = "$actual should have first element equal to $expected"
        assert(actual.first == expected) { message }
        return this
    }

    fun haveSecondEqualTo(expected: B): PairShould<A, B> {
        val message = "$actual should have second element equal to $expected"
        assert(actual.second == expected) { message }
        return this
    }
}