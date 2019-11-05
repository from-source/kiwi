package io.github.fromsource.kiwi.core.file

import io.github.fromsource.kiwi.core.BeEqual
import java.io.File
import java.nio.file.Path

class PathShould(private val actual: Path) : BeEqual<Path, PathShould> {
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
}