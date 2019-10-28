package io.github.fromsource.kiwi

data class Animal(val name: String, val weight: Int, val mammal: Boolean) {
    fun heavy(): Boolean = weight > 10
}