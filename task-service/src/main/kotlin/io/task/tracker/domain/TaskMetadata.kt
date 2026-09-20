package io.task.tracker.domain

import java.time.Instant
import java.util.*

data class TaskMetadata(
    var namespaceId: UUID,
    var parentId: UUID?,
    val createdAt: Instant,
    var updatedAt: Instant = createdAt
) {
    init {
        require(createdAt <= updatedAt) {
            "createdAt must be less than or equal to updatedAt"
        }
    }
}