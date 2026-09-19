package io.task.tracker.core.extension

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class LoggerExtensionTests {
    @Test
    fun `logger should create instance of Logger of needed generic type`() {
        val logger = logger<LoggerExtensionTests>()
        assertThat(logger.name).isEqualTo(LoggerExtensionTests::class.qualifiedName)
    }
}