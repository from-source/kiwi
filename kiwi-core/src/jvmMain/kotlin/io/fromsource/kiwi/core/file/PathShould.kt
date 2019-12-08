package io.fromsource.kiwi.core.file

import io.fromsource.kiwi.core.BeAny
import io.fromsource.kiwi.core.BeEqual
import java.io.File
import java.nio.file.Path

class PathShould(private val actual: Path) :
        BeAny<Path, PathShould>,
        BeEqual<Path, PathShould> {

    override fun actual(): Path = actual

    fun exist(): PathShould {
        val message = "Path '$actual' does not exist"
        assert(File(actual.toUri()).exists()) { message }
        return this
    }

    fun beADirectory(): PathShould {
        val message = "Path '$actual' is not a directory"
        assert(File(actual.toUri()).isDirectory) { message }
        return this
    }

    fun beAFile(): PathShould {
        val message = "Path '$actual' is not a file"
        assert(File(actual.toUri()).isFile) { message }
        return this
    }

    fun haveParentDirectory(userAssertedDirectory: String): PathShould {
        val userAssertedDirectoryPath = File(userAssertedDirectory).absolutePath
        val actualParentDirectory = File(actual.toUri()).parent
        val message = "Parent directory '$actualParentDirectory' does not match value '$userAssertedDirectoryPath'"
        assert(userAssertedDirectoryPath == actualParentDirectory) { message }
        return this
    }

    fun beAbsolute(): PathShould {
        val message = "Path '$actual' is not absolute"
        assert(File(actual.toString()).isAbsolute) { message }
        return this
    }
}