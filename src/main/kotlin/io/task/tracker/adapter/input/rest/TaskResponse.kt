package io.task.tracker.adapter.input.rest

import io.task.tracker.domain.*
import java.util.*

data class TaskResponse(
    val id: UUID,
    val info: TaskInfo,
    val state: TaskState,
    val attachments: List<TaskAttachment>,
    val subtasks: List<Task>,
    val metadata: TaskMetadata
)