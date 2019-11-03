package io.github.fromsource.kiwi.extension

import io.github.fromsource.kiwi.Animal
import io.github.fromsource.kiwi.core.should
import org.junit.jupiter.api.Test

class KiwiExtensionTest {

    @Test
    fun `should apply collections & string operators for list of custom data class`() {
        val kiwi = Animal(name = "kiwi", weight = 1, mammal = true)
        val hedgehog = Animal(name = "hedgehog", weight = 2, mammal = true)
        val flamingo = Animal(name = "flamingo", weight = 5, mammal = false)
        val humpback = Animal(name = "humpback", weight = 5000, mammal = true)

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
}