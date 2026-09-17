package io.tracker.task.adapter.input.rest

import io.tracker.task.core.port.input.CreateTaskCommand
import io.tracker.task.domain.Task
import io.tracker.task.domain.TaskPriority
import io.tracker.task.domain.TaskProgress
import io.tracker.task.domain.TaskAttachment
import io.tracker.task.domain.TaskStatus
import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class CreateTaskRequest(
    @NotBlank
    val title: String,
    val description: String,
    val priority: TaskPriority,
    val status: TaskStatus,
    val progress: TaskProgress,
    val attachments: List<TaskAttachment>,
    val subtasks: List<Task>,
    val userId: UUID,
    val projectId: UUID,
    val parentId: UUID?
) {
    fun toCommand(): CreateTaskCommand {
        return CreateTaskCommand(title, description, priority, status, progress, attachments, subtasks, userId, projectId, parentId)
    }
}