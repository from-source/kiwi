package io.fromsource.kiwi.core.range

import io.fromsource.kiwi.core.BeAny
import io.fromsource.kiwi.core.BeEqual

class IntRangeShould(private val actual: IntRange) :
        BeAny<IntRange, IntRangeShould>,
        BeEqual<IntRange, IntRangeShould> {

    override fun actual(): IntRange = actual
}