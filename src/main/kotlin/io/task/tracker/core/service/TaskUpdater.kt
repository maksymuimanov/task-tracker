package io.task.tracker.core.service

import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.UpdateTaskCommand
import io.task.tracker.core.port.input.UpdateTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskStatus
import io.task.tracker.mapper.TaskMapper
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.*

private val log = logger<TaskUpdater>()

@Component
class TaskUpdater(
    private val taskMapper: TaskMapper,
    private val taskRepository: TaskRepository,
    private val clock: Clock
) : UpdateTaskUseCase {
    override fun updateTask(
        id: UUID,
        command: UpdateTaskCommand
    ): Task {
        log.info("Updating task by id [id={}]", id)
        val task = taskRepository.findTaskById(id)
        taskMapper.updateTask(task, command, clock.instant())
        return taskRepository.saveTask(task)
    }

    override fun updateTaskStatus(
        id: UUID,
        status: TaskStatus
    ): Task {
        log.info("Updating task status by id [id={}]", id)
        val task = taskRepository.findTaskById(id)
        task.state.status = status
        task.metadata.updatedAt = clock.instant()
        return taskRepository.saveTask(task)
    }
}