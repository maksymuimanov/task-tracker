package io.task.tracker.core.service

import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.UpdateTaskCommand
import io.task.tracker.core.port.input.UpdateTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskProgress
import io.task.tracker.domain.TaskStatus
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.UUID

private val log = logger<TaskUpdater>()

@Component
class TaskUpdater(
    private val taskRepository: TaskRepository,
    private val clock: Clock
) : UpdateTaskUseCase {
    override fun updateTask(
        id: UUID,
        command: UpdateTaskCommand
    ): Task {
        log.info("Updating task by id [id={}]", id)
        val task = taskRepository.findTaskById(id)
        updateTaskInfo(task, command)
        updateTaskState(task, command)
        updateTaskMetadata(task, command)
        task.metadata.updatedAt = clock.instant()
        return taskRepository.saveTask(task)
    }

    private fun updateTaskInfo(task: Task, command: UpdateTaskCommand) {
        task.info.apply {
            title = command.title ?: title
            description = command.description ?: description
        }
    }

    private fun updateTaskState(task: Task, command: UpdateTaskCommand) {
        task.state.apply {
            priority = command.priority ?: priority
            status = command.status ?: status
            progress = command.progress ?: progress
        }
    }

    private fun updateTaskMetadata(task: Task, command: UpdateTaskCommand) {
        task.metadata.apply {
            namespaceId = command.namespaceId ?: namespaceId
            parentId = command.parentId ?: parentId
        }
    }

    override fun updateTaskStatus(
        id: UUID,
        status: TaskStatus
    ): Task {
        log.info("Updating task status by id [id={}]", id)
        val task = taskRepository.findTaskById(id)
        task.state.status = status
        updateProgressByStatus(task, status)
        task.metadata.updatedAt = clock.instant()
        return taskRepository.saveTask(task)
    }

    private fun updateProgressByStatus(task: Task, status: TaskStatus) {
        task.state.progress = when (status) {
            TaskStatus.NOT_STARTED -> TaskProgress(0)
            TaskStatus.COMPLETED -> TaskProgress(100)
            else -> task.state.progress
        }
    }
}