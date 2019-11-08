package io.fromsource.kiwi.core

import io.fromsource.kiwi.core.file.PathShould
import java.nio.file.Path

fun Path.should() = PathShould(this)
