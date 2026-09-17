package io.tracker.task.core.port.input

import io.tracker.task.domain.Task
import io.tracker.task.domain.TaskPriority
import io.tracker.task.domain.TaskProgress
import io.tracker.task.domain.TaskAttachment
import io.tracker.task.domain.TaskStatus
import java.util.UUID

data class UpdateTaskCommand(
    val title: String?,
    val description: String?,
    val priority: TaskPriority?,
    val status: TaskStatus?,
    val progress: TaskProgress?,
    val attachments: List<TaskAttachment>,
    val subtasks: List<Task>,
    val userId: UUID?,
    val projectId: UUID?,
    val parentId: UUID?
)