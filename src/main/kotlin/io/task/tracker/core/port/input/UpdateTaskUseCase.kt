package io.task.tracker.core.port.input

import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskStatus
import java.util.*

interface UpdateTaskUseCase {
    suspend fun updateTask(id: UUID, command: UpdateTaskCommand): Task

    suspend fun updateTaskStatus(id: UUID, status: TaskStatus): Task
}