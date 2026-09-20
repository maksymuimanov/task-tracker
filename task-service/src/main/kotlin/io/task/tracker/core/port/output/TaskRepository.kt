package io.task.tracker.core.port.output

import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TaskRepository {
    suspend fun saveTask(task: Task): Task

    suspend fun findTaskById(id: UUID): Task?

    fun findAllHeadTasks(namespaceId: UUID, pageInfo: PageInfo): Flow<Task>

    fun findAllTasksByParentId(parentId: UUID, pageInfo: PageInfo): Flow<Task>

    suspend fun deleteTaskById(id: UUID)
}