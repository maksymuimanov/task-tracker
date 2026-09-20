package io.task.tracker.core.port.input

import io.task.tracker.domain.Task

interface CreateTaskUseCase {
    suspend fun createTask(command: CreateTaskCommand): Task
}