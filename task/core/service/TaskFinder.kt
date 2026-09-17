package io.tracker.task.core.service

import io.tracker.task.core.extension.logger
import io.tracker.task.core.port.input.CreateTaskCommand
import io.tracker.task.core.port.input.CreateTaskUseCase
import io.tracker.task.core.port.input.FindTaskUseCase
import io.tracker.task.core.port.output.TaskRepository
import io.tracker.task.domain.PageInfo
import io.tracker.task.domain.Task
import io.tracker.task.domain.TaskInfo
import io.tracker.task.domain.TaskMetadata
import io.tracker.task.domain.TaskState
import org.springframework.stereotype.Component
import java.time.Clock
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

    override fun findAllParentTasksByProjectId(
        projectId: UUID,
        pageInfo: PageInfo
    ): List<Task> {
        log.info("Finding all parent tasks by project id [projectId={}]", projectId)
        return taskRepository.findAllParentTasksByProjectId(projectId, pageInfo)
    }

    override fun findAllParentTasksByUserId(
        userId: UUID,
        pageInfo: PageInfo
    ): List<Task> {
        log.info("Finding all parent tasks by user id [userId={}]", userId)
        return taskRepository.findAllParentTasksByUserId(userId, pageInfo)
    }
}