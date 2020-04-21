package org.fromsource.kiwi.core.range

import org.fromsource.kiwi.core.should
import kotlin.test.Test

class CharRangeShouldTest {

    @Test
    fun `should fail when ranges are not equal`() {
        runCatching {
            ('a'..'c').should beEqual ('a'..'b')
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should == a..b")
    }

    @Test
    fun `should guarantee ranges are equal`() {
        ('a'..'c').should beEqual ('a'..'c')
    }

    @Test
    fun `should fail when ranges are equal`() {
        runCatching {
            ('a'..'c').should notBeEqual ('a'..'c')
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should != a..c")
    }

    @Test
    fun `should guarantee ranges are not equal`() {
        ('a'..'c').should notBeEqual ('a'..'d')
    }

    @Test
    fun `should fail when range does not match condition`() {
        runCatching {
            ('a'..'c').should match { it.isEmpty() }
        }.should
                .beFailure(AssertionError::class)
    }

    @Test
    fun `should guarantee range matches condition`() {
        ('a'..'c').should match { !it.isEmpty() }
    }

    @Test
    fun `should fail when range matches condition`() {
        runCatching {
            ('a'..'c').should notMatch { it.first == 'a' }
        }.should
                .beFailure(AssertionError::class)
    }

    @Test
    fun `should guarantee range does not match condition`() {
        ('a'..'c').should notMatch { it.first == 'd' }
    }

    @Test
    fun `should fail when range does not start with`() {
        runCatching {
            ('a'..'c').should startWith 'b'
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should start with b")
    }

    @Test
    fun `should guarantee range starts with`() {
        ('a'..'c').should startWith 'a'
    }

    @Test
    fun `should fail when range starts with`() {
        runCatching {
            ('a'..'c').should notStartWith 'a'
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should not start with a")
    }

    @Test
    fun `should guarantee range does not starts with`() {
        ('a'..'c').should notStartWith 'b'
    }

    @Test
    fun `should fail when range does not end with`() {
        runCatching {
            ('a'..'c').should endWith 'b'
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should end with b")
    }

    @Test
    fun `should guarantee range ends with`() {
        ('a'..'c').should endWith 'c'
    }

    @Test
    fun `should fail when range end with`() {
        runCatching {
            ('a'..'c').should notEndWith 'c'
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should not end with c")
    }

    @Test
    fun `should guarantee range does not end with`() {
        ('a'..'c').should notEndWith 'b'
    }

    @Test
    fun `should fail when range does not contain`() {
        runCatching {
            ('a'..'c').should contain 'd'
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should contain d")
    }

    @Test
    fun `should guarantee range contains`() {
        ('a'..'c').should contain 'b'
    }

    @Test
    fun `should fail when range contains`() {
        runCatching {
            ('a'..'c').should notContain 'b'
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should not contain b")
    }

    @Test
    fun `should guarantee range does not contain`() {
        ('a'..'c').should notContain 'd'
    }

    @Test
    fun `should fail when range is empty`() {
        runCatching {
            ('a'..'c').should.beEmpty()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..c should be empty")
    }

    @Test
    fun `should guarantee range is empty`() {
        CharRange.EMPTY.should.beEmpty()
    }

    @Test
    fun `should fail when range is not empty`() {
        runCatching {
            CharRange.EMPTY.should.notBeEmpty()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("${CharRange.EMPTY} should not be empty")
    }

    @Test
    fun `should guarantee range is not empty`() {
        ('a'..'c').should.notBeEmpty()
    }

    @Test
    fun `should guarantee range overlaps`() {
        ('a'..'d').should.overlap(('a'..'c'))
        ('a'..'d').should.overlap(('b'..'d'))
    }

    @Test
    fun `should fail when range does not overlap`() {
        runCatching {
            ('a'..'d').should.overlap(('e'..'f'))
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("a..d should overlap e..f")
    }

    @Test
    fun `should combine assertions`() {
        ('a'..'c').should
                .notBeEmpty()
                .contain('b')
    }
}
