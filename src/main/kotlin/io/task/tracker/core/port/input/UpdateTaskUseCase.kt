package io.task.tracker.core.port.input

import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskStatus
import java.util.UUID

interface UpdateTaskUseCase {
    fun updateTask(id: UUID, command: UpdateTaskCommand): Task

    fun updateTaskStatus(id: UUID, status: TaskStatus): Task
}