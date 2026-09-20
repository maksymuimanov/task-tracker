package io.task.tracker.domain

import java.net.URI

sealed class TaskAttachment(
    open val content: Any
) {
    data class Base64(
        override val content: String
    ) : TaskAttachment(content)

    data class Url(
        override val content: URI
    ) : TaskAttachment(content)
}
