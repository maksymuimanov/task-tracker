package io.task.tracker.core.service

import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.FindTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import org.springframework.stereotype.Component
import java.util.*

private val log = logger<TaskFinder>()

@Component
class TaskFinder(
    private val taskRepository: TaskRepository
) : FindTaskUseCase {
    override fun findTaskById(id: UUID): Task {
        log.info("Finding task by id [id={}]", id)
        return taskRepository.findTaskById(id)
    }

    override fun findAllHeadTasks(pageInfo: PageInfo): List<Task> {
        log.info("Finding all head tasks [page={}, size={}]", pageInfo.page, pageInfo.size)
        return taskRepository.findAllHeadTasks(pageInfo)
    }

    override fun findAllTasksByParentId(
        parentId: UUID,
        pageInfo: PageInfo
    ): List<Task> {
        log.info("Finding tasks by parent id [parentId={}, page={}, size={}]", parentId, pageInfo.page, pageInfo.size)
        return taskRepository.findAllTasksByParentId(parentId, pageInfo)
    }
}