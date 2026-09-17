package io.task.tracker.domain

import java.util.UUID

data class Task(
    val id: UUID,
    val info: TaskInfo,
    val state: TaskState,
    val attachments: List<TaskAttachment>,
    val subtasks: List<Task>,
    val metadata: TaskMetadata
) {
    val isParent: Boolean = metadata.parentId == null
}