package org.fromsource.kiwi.json

import org.fromsource.kiwi.core.should
import kotlin.test.Test

class ExtensionTest {

    @Test
    fun `should return pair - empty and actual list when limit is 0`() {
        "This is a sentence".toCharList()
                .split('a', 0)
                .should
                .haveFirstEqualTo("This is a sentence".toCharList())
                .haveSecondEqualTo("".toCharList())
    }

    @Test
    fun `should return pair - empty and actual list when token is not found`() {
        "This is a sentence".toCharList()
                .split('-', 1)
                .should
                .haveFirstEqualTo("This is a sentence".toCharList())
                .haveSecondEqualTo("".toCharList())
    }

    @Test
    fun `should return partially split list when limit is not reached`() {
        "This is a sentence".toCharList()
                .split('-', 0)
                .should
                .haveFirstEqualTo("This is a sentence".toCharList())
                .haveSecondEqualTo("".toCharList())
    }

    @Test
    fun `should split list based on token`() {
        "This is a sentence".toCharList()
                .split('a', 1)
                .should
                .haveFirstEqualTo("This is a".toCharList())
                .haveSecondEqualTo(" sentence".toCharList())
    }

    @Test
    fun `should split list based on token and limit`() {
        """ "key": "value" """.toCharList()
                .split('"', 2)
                .should
                .haveFirstEqualTo(""" "key"""".toCharList())
                .haveSecondEqualTo(""": "value" """.toCharList())
    }

    @Test
    fun `should handle escape`() {
        """ "\"key\"" : "value" """.toCharList()
                .split('"', 2)
                .should
                .haveFirstEqualTo(""" "\"key\""""".toCharList())
                .haveSecondEqualTo(""" : "value" """.toCharList())
    }

    @Test
    fun `should handle escape characters in string`() {
        """ "\\" } """.toCharList()
                .split('"', 2)
                .should
                .haveFirstEqualTo(""" "\\"""".toCharList())
                .haveSecondEqualTo(" } ".toCharList())
    }
}