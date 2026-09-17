package io.tracker.task.core.port.input

import io.tracker.task.domain.Task

interface CreateTaskUseCase {
    fun createTask(command: CreateTaskCommand): Task
}