package io.fromsource.kiwi.extension

import io.fromsource.kiwi.Animal
import io.fromsource.kiwi.core.should
import kotlin.test.Test

class KiwiExtensionTest {

    val kiwi = Animal(name = "kiwi", weight = 1, mammal = true)
    val hedgehog = Animal(name = "hedgehog", weight = 2, mammal = true)
    val flamingo = Animal(name = "flamingo", weight = 5, mammal = false)
    val humpback = Animal(name = "humpback", weight = 5000, mammal = true)

    @Test
    fun `should apply collections & string operators for list of custom data class`() {
        val animals = listOf(kiwi, hedgehog, flamingo, humpback)

        animals.should()
                .haveSize(4)
                .contain(flamingo)
                .have2nd(hedgehog)
                .filtered { animal -> animal.mammal }
                .matchAny { animal -> animal.heavy() }
                .last().name.should()
                .match(Regex("[a-z]+"))
    }

    @Test
    fun `should sort custom data class collection by property`() {
        val animals = listOf(flamingo, hedgehog, humpback, kiwi)

        animals.should()
            .beSorted { it.name }
    }
}