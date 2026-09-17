package io.tracker.task.core.service

import io.tracker.task.core.extension.logger
import io.tracker.task.core.port.input.CreateTaskCommand
import io.tracker.task.core.port.input.CreateTaskUseCase
import io.tracker.task.core.port.input.UpdateTaskCommand
import io.tracker.task.core.port.input.UpdateTaskUseCase
import io.tracker.task.core.port.output.TaskRepository
import io.tracker.task.domain.Task
import io.tracker.task.domain.TaskInfo
import io.tracker.task.domain.TaskMetadata
import io.tracker.task.domain.TaskProgress
import io.tracker.task.domain.TaskState
import io.tracker.task.domain.TaskStatus
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
            userId = command.userId ?: userId
            projectId = command.projectId ?: projectId
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