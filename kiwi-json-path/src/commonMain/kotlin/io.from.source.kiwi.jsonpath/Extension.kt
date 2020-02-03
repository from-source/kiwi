package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.Json

fun Json.evaluatePath(path: String): List<Json> = JsonPath.evaluate(this, path)