package io.task.tracker.core.port.input

import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskPriority
import io.task.tracker.domain.TaskProgress
import io.task.tracker.domain.TaskAttachment
import io.task.tracker.domain.TaskStatus
import java.util.UUID

data class UpdateTaskCommand(
    val title: String?,
    val description: String?,
    val priority: TaskPriority?,
    val status: TaskStatus?,
    val progress: TaskProgress?,
    val attachments: List<TaskAttachment>,
    val subtasks: List<Task>,
    val namespaceId: UUID?,
    val parentId: UUID?
)