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
import java.util.*

private val log = logger<TaskCreator>()

@Component
class TaskCreator(
    private val taskRepository: TaskRepository,
    private val clock: Clock
) : CreateTaskUseCase {
    override fun createTask(command: CreateTaskCommand): Task {
        log.info("Creating task [title={}]", command.title)
        val task = command.toTask()
        return taskRepository.saveTask(task)
    }

    private fun CreateTaskCommand.toTask(): Task {
        val taskInfo = TaskInfo(title, description)
        val taskState = TaskState(priority, status, progress)
        val createdAt = clock.instant()
        val taskMetadata = TaskMetadata(parentId, createdAt, createdAt)
        val task = Task(
            id = UUID.randomUUID(),
            info = taskInfo,
            state = taskState,
            attachments = attachments,
            subtasks = subtasks,
            metadata = taskMetadata
        )
        return task
    }
}