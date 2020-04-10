package org.fromsource.kiwi.jsonpath

import org.fromsource.kiwi.core.should
import kotlin.test.Test

class SelectorTest {

    @Test
    fun `should create single positive index`() {
        val selector = Selector.create("1")

        selector.should beEqual SingleIndex(1)
    }

    @Test
    fun `should create single negative index`() {
        val selector = Selector.create(" -1 ")

        selector.should beEqual SingleIndex(-1)
    }

    @Test
    fun `should create multiple index selector`() {
        val selector = Selector.create("-1, 2,1 ")

        selector.should beEqual MultipleIndex(listOf(-1, 2, 1))
    }

    @Test
    fun `should create forward slice selector`() {
        val selector = Selector.create(":2")

        selector.should beEqual Slice(":2")
    }

    @Test
    fun `should create backward slice selector`() {
        val selector = Selector.create(":-2")

        selector.should beEqual Slice(":-2")
    }

    @Test
    fun `should create all selector`() {
        val selector = Selector.create(" * ")

        selector.should beEqual All
    }

    @Test
    fun `should create no op selector when it can't mach`() {
        val selector = Selector.create("?")

        selector.should beEqual NoOp
    }

    @Test
    fun `should return single index selector based on multiple index selector`() {
        val selector = MultipleIndex(listOf(-1, 2))

        selector.indexes().should beEqual listOf(SingleIndex(-1), SingleIndex(2))
    }

    @Test
    fun `should return single index selector slice selector`() {
        val slice = Slice(":2")

        slice.indexes().should beEqual listOf(SingleIndex(0), SingleIndex(1))
    }
}