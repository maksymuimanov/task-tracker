package io.task.tracker.core.port.input

import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import java.util.UUID

interface FindTaskUseCase {
    fun findTaskById(id: UUID): Task

    fun findAllParentTasksByNamespaceId(namespaceId: UUID, pageInfo: PageInfo): List<Task>
}