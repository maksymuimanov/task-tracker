package io.task.tracker.core.port.input

import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import java.util.*

interface FindTaskUseCase {
    fun findTaskById(id: UUID): Task

    fun findAllHeadTasks(pageInfo: PageInfo): List<Task>

    fun findAllTasksByParentId(parentId: UUID, pageInfo: PageInfo): List<Task>
}