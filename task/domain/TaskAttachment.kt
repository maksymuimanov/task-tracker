package io.tracker.task.domain

import java.net.URL
import java.util.UUID

sealed class TaskAttachment(
    open val id: UUID
) {
    data class Base64Attachment(
        override val id: UUID,
        val content: String
    ) : TaskAttachment(id)

    data class UrlAttachment(
        override val id: UUID,
        val url: URL
    ) : TaskAttachment(id)
}
