package io.task.tracker.core.port.input

import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskAttachment
import io.task.tracker.domain.TaskPriority
import io.task.tracker.domain.TaskStatus
import java.util.*

data class CreateTaskCommand(
    val title: String,
    val description: String,
    val priority: TaskPriority,
    val status: TaskStatus,
    val attachments: List<TaskAttachment>,
    val subtasks: List<Task>,
    val parentId: UUID?
) {
    init {
        require(title.isNotBlank()) {
            "Title cannot be blank"
        }
    }
}