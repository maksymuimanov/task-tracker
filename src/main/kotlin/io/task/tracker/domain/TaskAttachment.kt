package io.task.tracker.domain

import java.net.URL
import java.util.*

sealed class TaskAttachment(
    open val id: UUID = UUID.randomUUID()
) {
    data class Base64(
        val content: String
    ) : TaskAttachment()

    data class Url(
        val url: URL
    ) : TaskAttachment()
}
