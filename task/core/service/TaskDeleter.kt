package io.tracker.task.core.service

import io.tracker.task.core.extension.logger
import io.tracker.task.core.port.input.DeleteTaskUseCase
import io.tracker.task.core.port.input.FindTaskUseCase
import io.tracker.task.core.port.input.CreateTaskUseCase
import io.tracker.task.core.port.input.UpdateTaskUseCase
import io.tracker.task.core.port.output.TaskRepository
import org.springframework.stereotype.Service
import java.time.Clock
import java.util.UUID

private val log = logger<TaskDeleter>()

@Service
class TaskDeleter(
    private val taskRepository: TaskRepository
) : DeleteTaskUseCase {

    override fun deleteTaskById(id: UUID) {
        log.info("Deleting task by id [id={}]", id)
        taskRepository.deleteTaskById(id)
    }
}