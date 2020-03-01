package org.fromsource.kiwi.core.collection

import org.fromsource.kiwi.core.should
import kotlin.test.Test

class CollectionShouldTest {

    @Test
    fun `should fail when list of Bytes are not sum properly`() {
        val numbers: List<Byte> = listOf(1, 2, 3)

        runCatching {
            numbers.should sumTo 7
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$numbers should sum to: 7")
    }

    @Test
    fun `should guarantee list of Bytes are sum properly`() {
        val numbers: List<Byte> = listOf(1, 2, Byte.MAX_VALUE)

        numbers.should sumTo 130
    }

    @Test
    fun `should fail when list of Shorts are not sum properly`() {
        val numbers: List<Short> = listOf(1, 2, 3)

        runCatching {
            numbers.should sumTo 7
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$numbers should sum to: 7")
    }

    @Test
    fun `should guarantee list of Shorts are sum properly`() {
        val numbers: List<Short> = listOf(1, 2, Short.MAX_VALUE)

        numbers.should sumTo 32770
    }

    @Test
    fun `should fail when list of Ints are not sum properly`() {
        val numbers: List<Int> = listOf(1, 2, 3)

        runCatching {
            numbers.should sumTo 7
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$numbers should sum to: 7")
    }

    @Test
    fun `should guarantee list of Ints are sum properly`() {
        val numbers: List<Int> = listOf(1, Int.MAX_VALUE.dec())

        numbers.should sumTo Int.MAX_VALUE
    }

    @Test
    fun `should fail when list of Longs are not sum properly`() {
        val numbers: List<Long> = listOf(1, 2, 3)

        runCatching {
            numbers.should sumTo 7
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$numbers should sum to: 7")
    }

    @Test
    fun `should guarantee list of Longs are sum properly`() {
        val numbers: List<Long> = listOf(1, 2, Int.MAX_VALUE.toLong())

        numbers.should sumTo 2147483650
    }

    @Test
    fun `should fail when list of Floats are not sum properly`() {
        val numbers: List<Float> = listOf(1.1f, 2.1f, 3.1f)

        runCatching {
            numbers.should sumTo 7.7f
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$numbers should sum to: 7.7")
    }

    @Test
    fun `should guarantee list of Floats are sum properly`() {
        val numbers: List<Float> = listOf(1.1f, 2.1f, 3.1f)

        numbers.should sumTo numbers.sum()
    }

    @Test
    fun `should fail when list of Doubles are not sum properly`() {
        val numbers: List<Double> = listOf(1.1, 2.1, 3.1)

        runCatching {
            numbers.should sumTo 7.7
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$numbers should sum to: 7.7")
    }

    @Test
    fun `should guarantee list of Doubles are sum properly`() {
        val numbers: List<Double> = listOf(1.1, 2.1, 3.1)

        numbers.should sumTo numbers.sum()
    }
}