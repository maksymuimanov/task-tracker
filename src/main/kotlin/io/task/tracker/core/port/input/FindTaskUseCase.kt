package io.task.tracker.core.port.input

import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

interface FindTaskUseCase {
    suspend fun findTaskById(id: UUID): Task

    fun findAllHeadTasks(pageInfo: PageInfo): Flow<Task>

    fun findAllTasksByParentId(parentId: UUID, pageInfo: PageInfo): Flow<Task>
}