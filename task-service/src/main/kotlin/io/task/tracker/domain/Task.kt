package io.task.tracker.domain

import java.util.*

data class Task(
    val id: UUID,
    val info: TaskInfo,
    val state: TaskState,
    var attachments: List<TaskAttachment>,
    val metadata: TaskMetadata
)