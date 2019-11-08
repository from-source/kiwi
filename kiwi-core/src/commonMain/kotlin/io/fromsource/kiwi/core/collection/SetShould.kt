package io.fromsource.kiwi.core.collection

open class SetShould<T>(override val actual: Set<T>) : CollectionShould<T, SetShould<T>>(actual)