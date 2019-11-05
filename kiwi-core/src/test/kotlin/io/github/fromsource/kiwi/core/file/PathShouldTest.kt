package io.github.fromsource.kiwi.core.file

import io.github.fromsource.kiwi.core.should
import org.junit.jupiter.api.Test
import java.io.File

class PathShouldTest {

    @Test
    fun `should be able to verify if path exist`() {
        File("./src/test/resources/sample.txt").toPath().should().exist()
    }

    @Test
    fun `should fail if path does not exist`() {
        val path = File("./src/test/resources/non_existent.txt").toPath()

        runCatching {
            path.should().exist()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Path './src/test/resources/non_existent.txt' does not exist")
    }

    @Test
    fun `should be able to verify if path points to a directory`() {
        File("./src/test/resources").toPath().should().beADirectory()
    }

    @Test
    fun `should fail if path does not point to a directory`() {
        val path = File("./src/test/resources/sample.txt").toPath()

        runCatching {
            path.should().beADirectory()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Path './src/test/resources/sample.txt' is not a directory")
    }

    @Test
    fun `should be able to verify if path points to a file`() {
        File("./src/test/resources/sample.txt").toPath().should().beAFile()
    }

    @Test
    fun `should fail if path does not point to a file`() {
        val path = File("./src/test/resources").toPath()

        runCatching {
            path.should().beAFile()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Path './src/test/resources' is not a file")
    }
}