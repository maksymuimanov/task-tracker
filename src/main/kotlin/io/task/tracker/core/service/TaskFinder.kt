package io.task.tracker.core.service

import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.FindTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import org.springframework.stereotype.Component
import java.util.UUID

private val log = logger<TaskFinder>()

@Component
class TaskFinder(
    private val taskRepository: TaskRepository
) : FindTaskUseCase {
    override fun findTaskById(id: UUID): Task {
        log.info("Finding task by id [id={}]", id)
        return taskRepository.findTaskById(id)
    }

    override fun findAllParentTasksByNamespaceId(
        namespaceId: UUID,
        pageInfo: PageInfo
    ): List<Task> {
        log.info("Finding all parent tasks by project id [projectId={}]", namespaceId)
        return taskRepository.findAllParentTasksByProjectId(namespaceId, pageInfo)
    }
}