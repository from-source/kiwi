package org.fromsource.kiwi.core

import kotlin.test.Test

class KiwiTest {

    @Test
    fun `should combine operator in infix chain`() {
        val animals = listOf("kiwi", "hedgehog", "flamingo", "humpback")

        animals.should haveSize 4 contain "humpback" have1st "kiwi"
    }

    @Test
    fun `should extract one of collection elements and apply other operator on it`() {
        val animals = listOf("kiwi", "hedgehog", "flamingo", "humpback")

        animals.should
                .haveSize(4)
                .have1st("kiwi")
                .contain("humpback")
                .matchAny { it.contains("go") }
                .last().should
                .notBeEmpty()
    }
}