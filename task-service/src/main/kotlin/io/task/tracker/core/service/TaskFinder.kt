package io.task.tracker.core.service

import io.task.tracker.core.exception.NotFoundException
import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.FindTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

private val log = logger<TaskFinder>()

class TaskFinder(
    private val taskRepository: TaskRepository
) : FindTaskUseCase {
    override suspend fun findTaskById(id: UUID): Task {
        log.info("Finding task by id [id={}]", id)
        return taskRepository.findTaskById(id) ?: throw NotFoundException("Task not found with id: $id")
    }

    override fun findAllHeadTasks(namespaceId: UUID, pageInfo: PageInfo): Flow<Task> {
        log.info("Finding all head tasks [page={}, size={}]", pageInfo.page, pageInfo.size)
        return taskRepository.findAllHeadTasks(namespaceId, pageInfo)
    }

    override fun findAllTasksByParentId(
        parentId: UUID,
        pageInfo: PageInfo
    ): Flow<Task> {
        log.info("Finding tasks by parent id [parentId={}, page={}, size={}]", parentId, pageInfo.page, pageInfo.size)
        return taskRepository.findAllTasksByParentId(parentId, pageInfo)
    }
}