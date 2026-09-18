package io.task.tracker.core.port.output

import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import java.util.*

interface TaskRepository {
    fun saveTask(task: Task): Task

    fun findTaskById(id: UUID): Task

    fun findAllParentTasks(pageInfo: PageInfo): List<Task>

    fun findAllParentTasksByParentId(parentId: UUID?, pageInfo: PageInfo): List<Task>

    fun deleteTaskById(id: UUID)
}