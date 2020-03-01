package io.fromsource.kiwi.jsonpath

import io.fromsource.kiwi.json.Json

fun Json.evaluatePath(path: String): List<Json> = JsonPath.evaluate(this, path)