package io.task.tracker.core.port.input

import java.util.*

interface DeleteTaskUseCase {
    suspend fun deleteTaskById(id: UUID)
}