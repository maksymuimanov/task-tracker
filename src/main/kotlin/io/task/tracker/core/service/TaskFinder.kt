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

    override fun findAllParentTasks(pageInfo: PageInfo): List<Task> {
        TODO("Not yet implemented")
    }

    override fun findAllParentTasksByParentId(
        parentId: UUID,
        pageInfo: PageInfo
    ): List<Task> {
        TODO("Not yet implemented")
    }
}