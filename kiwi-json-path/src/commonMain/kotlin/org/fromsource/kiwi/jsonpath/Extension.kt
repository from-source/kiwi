package org.fromsource.kiwi.jsonpath

import org.fromsource.kiwi.json.Json

fun Json.evaluatePath(path: String): List<Json> = JsonPath.evaluate(this, path)

fun String.between(prefix: Char, suffix: Char) = this.substring(this.indexOfFirst { it == prefix } + 1, this.indexOfFirst { it == suffix })
fun <E> List<E>.head(): E = this.first()

fun String.tail(): String = if (this.isEmpty()) throw RuntimeException("Empty string") else this.drop(1)
fun String.head(): String = this.take(1)
