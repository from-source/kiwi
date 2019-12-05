package io.fromsource.kiwi.core

class AnyShould(val actual: Any) : BeAny<Any, AnyShould> {
    override fun actual(): Any = actual
}