package org.fromsource.kiwi.jsonpath

import org.fromsource.kiwi.json.Json

fun Json.evaluatePath(path: String): List<Json> = JsonPath.evaluate(this, path)