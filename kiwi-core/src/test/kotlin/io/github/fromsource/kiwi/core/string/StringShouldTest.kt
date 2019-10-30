package io.github.fromsource.kiwi.core.string

import io.github.fromsource.kiwi.core.util.runCatching
import io.github.fromsource.kiwi.core.should
import org.junit.jupiter.api.Test
import java.math.BigInteger

class StringShouldTest {

    @Test
    fun `should fail when string is not empty`() {
        runCatching {
            "hello kiwi".should().beEmpty()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should be empty")
    }

    @Test
    fun `should guarantee string is empty`() {
        "".should().beEmpty()
    }

    @Test
    fun `should fail when string is empty`() {
        runCatching {
            "".should().notBeEmpty()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'' should not be empty")
    }

    @Test
    fun `should guarantee string is not empty`() {
        "kiwi!".should().notBeEmpty()
    }

    @Test
    fun `should fail when string is not blank`() {
        runCatching {
            "hello kiwi".should().beBlank()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should be blank")
    }

    @Test
    fun `should guarantee string is blank`() {
        " ".should().beBlank()
    }

    @Test
    fun `should fail when strings are not equal`() {
        runCatching {
            "hello kiwi".should() beEqual "hello kiwi!"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should == 'hello kiwi!'")
    }

    @Test
    fun `should guarantee strings are equal`() {
        "hello kiwi".should() beEqual "hello kiwi"
    }

    @Test
    fun `should fail when string does not contain`() {
        runCatching {
            "hello kiwi".should() contain "loki"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should contain 'loki'")
    }

    @Test
    fun `should guarantee string contains`() {
        "hello kiwi".should() contain "lo ki"
    }

    @Test
    fun `should fail when string does not start with`() {
        runCatching {
            "hello kiwi".should() startWith "ello"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should start with 'ello'")
    }

    @Test
    fun `should guarantee string starts with`() {
        "hello kiwi".should() startWith "hello"
    }

    @Test
    fun `should fail when string does not end with`() {
        runCatching {
            "hello kiwi".should() endWith "kiw"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should end with 'kiw'")
    }

    @Test
    fun `should guarantee string ends with`() {
        "hello kiwi".should() endWith "kiwi"
    }

    @Test
    fun `should fail when string does not have proper length`() {
        runCatching {
            "hello kiwi".should() haveLength 9
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should have length 9")
    }

    @Test
    fun `should guarantee string has proper length`() {
        "hello kiwi".should() haveLength 10
    }

    @Test
    fun `should fail when string does is not capitalised`() {
        runCatching {
            "kIWI".should().beCapitalised()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'kIWI' should be capitalised")
    }

    @Test
    fun `should guarantee string is capitalised`() {
        "Kiwi".should().beCapitalised()
    }

    @Test
    fun `should fail when string does is not decapitalized`() {
        runCatching {
            "Kiwi".should().beDecapitalized()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'Kiwi' should be decapitalised")
    }

    @Test
    fun `should guarantee string is decapitalized`() {
        "kiwi".should().beDecapitalized()
    }

    @Test
    fun `should fail when string does not mach regex`() {
        runCatching {
            "hello kiwi 101".should() match Regex("[a-z]+")
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi 101' should match '[a-z]+'")
    }

    @Test
    fun `should guarantee string match regex`() {
        "hello kiwi 101".should() match Regex("\\w+\\s\\w+\\s[0-1]{3}")
    }

    @Test
    fun `should fail when string is not lowercase`() {
        runCatching {
            "hello Kiwi".should().beLowercase()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello Kiwi' should be lowercase")
    }

    @Test
    fun `should guarantee string is lowercase`() {
        "hello kiwi 101".should().beLowercase()
    }

    @Test
    fun `should fail when string is not uppercase`() {
        runCatching {
            "HELLO k !".should().beUppercase()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'HELLO k !' should be uppercase")
    }

    @Test
    fun `should guarantee string is uppercase`() {
        "HELLO KIWI 101".should().beUppercase()
    }

    @Test
    fun `should fail when string is not Long`() {
        val aboveLong = BigInteger.valueOf(Long.MAX_VALUE).plus(BigInteger.ONE)
        runCatching {
            "$aboveLong".should().beLong()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'$aboveLong' should be Long")
    }

    @Test
    fun `should guarantee string is Long`() {
        "${Long.MAX_VALUE}".should().beLong()
    }

    @Test
    fun `should fail when string is not Int`() {
        val aboveInt: Long = Int.MAX_VALUE + 1L
        runCatching {
            "$aboveInt".should().beInt()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'$aboveInt' should be Int")
    }

    @Test
    fun `should guarantee string is Int`() {
        "${Int.MAX_VALUE}".should().beInt()
    }

    @Test
    fun `should fail when string is not Short`() {
        val aboveShort = Short.MAX_VALUE + 1
        runCatching {
            "$aboveShort".should().beShort()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'$aboveShort' should be Short")
    }

    @Test
    fun `should guarantee string is Short`() {
        "${Short.MAX_VALUE}".should().beInt()
    }
}