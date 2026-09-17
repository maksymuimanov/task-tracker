package io.tracker.task.core.port.input

import io.tracker.task.domain.Task
import io.tracker.task.domain.TaskStatus
import java.util.UUID

interface UpdateTaskUseCase {
    fun updateTask(id: UUID, command: UpdateTaskCommand): Task

    fun updateTaskStatus(id: UUID, status: TaskStatus): Task
}