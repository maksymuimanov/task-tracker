package io.task.tracker.core.service

import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.CreateTaskCommand
import io.task.tracker.core.port.input.CreateTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.Task
import io.task.tracker.mapper.TaskMapper
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.*

private val log = logger<TaskCreator>()

@Component
class TaskCreator(
    private val taskMapper: TaskMapper,
    private val taskRepository: TaskRepository,
    private val clock: Clock
) : CreateTaskUseCase {
    override fun createTask(command: CreateTaskCommand): Task {
        log.info("Creating task [title={}]", command.title)
        val task = taskMapper.toTask(UUID.randomUUID(), command, clock.instant())
        return taskRepository.saveTask(task)
    }
}