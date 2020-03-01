package org.fromsource.kiwi.core

@Suppress("UNCHECKED_CAST")
interface BeAny<T, S : BeAny<T, S>> : BeEqual<T, S> {
    override fun actual(): T

    infix fun match(condition: (T) -> Boolean): S {
        val message = "${actual()} should match condition $condition"
        val result = condition(actual())
        assert(result) { message }
        return this as S
    }

    infix fun notMatch(condition: (T) -> Boolean): S {
        val message = "${actual()} should not match condition $condition"
        val result = condition(actual())
        assert(!result) { message }
        return this as S
    }
}