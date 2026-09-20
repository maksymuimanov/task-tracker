package io.task.tracker.adapter.input.rest

import io.task.tracker.domain.TaskAttachment
import io.task.tracker.domain.TaskPriority
import io.task.tracker.domain.TaskStatus
import jakarta.validation.constraints.NotBlank
import java.util.*

data class CreateTaskRequest(
    @NotBlank
    val title: String,
    val description: String,
    val priority: TaskPriority,
    val status: TaskStatus,
    val attachments: List<TaskAttachment>,
    val namespaceId: UUID,
    val parentId: UUID?
)
