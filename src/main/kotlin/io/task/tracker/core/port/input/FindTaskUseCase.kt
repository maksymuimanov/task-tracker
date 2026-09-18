package io.task.tracker.core.port.input

import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import java.util.*

interface FindTaskUseCase {
    fun findTaskById(id: UUID): Task

    fun findAllParentTasks(pageInfo: PageInfo): List<Task>

    fun findAllParentTasksByParentId(parentId: UUID, pageInfo: PageInfo): List<Task>
}