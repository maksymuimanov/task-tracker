package io.tracker.task.core.port.input

import io.tracker.task.domain.PageInfo
import io.tracker.task.domain.Task
import java.util.UUID

interface FindTaskUseCase {
    fun findTaskById(id: UUID): Task

    fun findAllParentTasksByProjectId(projectId: UUID, pageInfo: PageInfo): List<Task>

    fun findAllParentTasksByUserId(userId: UUID, pageInfo: PageInfo): List<Task>
}