package io.task.tracker.adapter.input.rest

import io.task.tracker.domain.TaskAttachment
import io.task.tracker.domain.TaskInfo
import io.task.tracker.domain.TaskMetadata
import io.task.tracker.domain.TaskState
import java.util.*

data class TaskResponse(
    val id: UUID,
    val info: TaskInfo,
    val state: TaskState,
    val attachments: List<TaskAttachment>,
    val metadata: TaskMetadata
)