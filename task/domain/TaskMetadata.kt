package io.tracker.task.domain

import java.time.Instant
import java.util.UUID

data class TaskMetadata(
    var userId: UUID,
    var projectId: UUID,
    var parentId: UUID?,
    val createdAt: Instant,
    var updatedAt: Instant
) {
    init {
        require(createdAt <= updatedAt) {
            "createdAt must be less than or equal to updatedAt"
        }
    }
}