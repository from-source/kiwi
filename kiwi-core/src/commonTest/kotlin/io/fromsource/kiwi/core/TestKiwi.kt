package io.fromsource.kiwi.core

import io.fromsource.kiwi.core.util.ResultShould

fun <T> Result<T>.should(): ResultShould<T> = ResultShould(this)