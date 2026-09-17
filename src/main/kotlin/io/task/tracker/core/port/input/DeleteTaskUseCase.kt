package io.task.tracker.core.port.input

import java.util.UUID

interface DeleteTaskUseCase {
    fun deleteTaskById(id: UUID)
}