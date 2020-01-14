package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class ExtensionTest {

    @Test
    fun `should return pair - empty and actual list when limit is 0`() {
        "This is a sentence".toCharList()
                .split('a', 0)
                .should()
                .haveFirstEqualTo("This is a sentence".toCharList())
                .haveSecondBeEqual("".toCharList())
    }

    @Test
    fun `should return pair - empty and actual list when token is not found`() {
        "This is a sentence".toCharList()
                .split('-', 1)
                .should()
                .haveFirstEqualTo("This is a sentence".toCharList())
                .haveSecondBeEqual("".toCharList())
    }

    @Test
    fun `should return partially splitted list when limit is not reached`() {
        "This is a sentence".toCharList()
                .split('-', 0)
                .should()
                .haveFirstEqualTo("This is a sentence".toCharList())
                .haveSecondBeEqual("".toCharList())
    }

    @Test
    fun `should split list based on token`() {
        "This is a sentence".toCharList()
                .split('a', 1)
                .should()
                .haveFirstEqualTo("This is a".toCharList())
                .haveSecondBeEqual(" sentence".toCharList())
    }

    @Test
    fun `should split list based on token and limit`() {
        """ "key": "value" """.toCharList()
                .split('"', 2)
                .should()
                .haveFirstEqualTo(""" "key"""".toCharList())
                .haveSecondBeEqual(""": "value" """.toCharList())
    }

    @Test
    fun `should read string from list of chars`() {
        """"this is some string" and something rest """.toCharList().nextString()
                .should()
                .haveFirstEqualTo("this is some string")
                .haveSecondBeEqual(" and something rest ".toCharList())
    }
}