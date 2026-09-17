package io.task.tracker.core.service

import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.CreateTaskCommand
import io.task.tracker.core.port.input.CreateTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskInfo
import io.task.tracker.domain.TaskMetadata
import io.task.tracker.domain.TaskState
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.UUID

private val log = logger<TaskCreator>()

@Component
class TaskCreator(
    private val taskRepository: TaskRepository,
    private val clock: Clock
) : CreateTaskUseCase {
    override fun createTask(command: CreateTaskCommand): Task {
        log.info("Creating task [title={}]", command.title)
        val task = mapCommandToTask(command)
        return taskRepository.saveTask(task)
    }

    private fun mapCommandToTask(command: CreateTaskCommand): Task {
        val taskInfo = TaskInfo(command.title, command.description)
        val taskState = TaskState(command.priority, command.status, command.progress)
        val createdAt = clock.instant()
        val taskMetadata = TaskMetadata(command.namespaceId, command.parentId, createdAt, createdAt)
        val task = Task(
            id = UUID.randomUUID(),
            info = taskInfo,
            state = taskState,
            attachments = command.attachments,
            subtasks = command.subtasks,
            metadata = taskMetadata
        )
        return task
    }
}