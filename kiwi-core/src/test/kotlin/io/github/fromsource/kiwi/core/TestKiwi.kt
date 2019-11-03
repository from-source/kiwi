package io.github.fromsource.kiwi.core

import io.github.fromsource.kiwi.core.util.ResultShould

fun <T> Result<T>.should(): ResultShould<T> = ResultShould(this)