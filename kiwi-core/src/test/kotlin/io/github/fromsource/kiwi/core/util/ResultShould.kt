package io.github.fromsource.kiwi.core.util

import kotlin.reflect.KClass

open class ResultShould<T>(private val actual: Result<T>) {

    infix fun beFailure(assertionError: KClass<AssertionError>): ResultShould<T> {
        if(!actual.isFailure()) {
            val message = "Expected $assertionError to be thrown"
            assert(false) { message }
        }

        val theSameFailure = assertionError.java == actual.throwableOrNull()?.javaClass
        val message = "expected: $assertionError != ${actual.throwableOrNull()} actual"
        assert(theSameFailure) { message }
        return this
    }

    infix fun beSuccess(element: T): ResultShould<T> {
        val isSuccess = element == actual.getOrNull()
        val message = "expected: $element != ${actual.getOrNull()} actual"
        assert(isSuccess) { message }
        return this
    }

    infix fun haveFailureMessage(failureMessage: String): ResultShould<T> {
        val actualMessage = actual.throwableOrNull()?.message
        val message = "expected: '$failureMessage' != '$actualMessage' actual"
        assert(actualMessage == failureMessage) { message }
        return this
    }
}