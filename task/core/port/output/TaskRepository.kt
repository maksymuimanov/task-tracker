package io.tracker.task.core.port.output

import io.tracker.task.domain.PageInfo
import io.tracker.task.domain.Task
import java.util.UUID

interface TaskRepository {
    fun saveTask(task: Task): Task

    fun findTaskById(id: UUID): Task

    fun findAllParentTasksByProjectId(projectId: UUID, pageInfo: PageInfo): List<Task>

    fun findAllParentTasksByUserId(userId: UUID, pageInfo: PageInfo): List<Task>

    fun deleteTaskById(id: UUID)
}