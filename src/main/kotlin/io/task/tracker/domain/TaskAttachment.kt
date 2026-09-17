package io.task.tracker.domain

import java.net.URL
import java.util.UUID

sealed class TaskAttachment(
    open val id: UUID = UUID.randomUUID()
) {
    data class Base64Attachment(
        val content: String
    ) : TaskAttachment()

    data class UrlAttachment(
        val url: URL
    ) : TaskAttachment()
}
