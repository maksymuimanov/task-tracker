package io.tracker.task.core.port.input

import io.tracker.task.domain.Task
import java.util.UUID

interface DeleteTaskUseCase {
    fun deleteTaskById(id: UUID)
}