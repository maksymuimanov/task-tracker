package io.task.tracker.core.service

import io.task.tracker.core.extension.logger
import io.task.tracker.core.port.input.DeleteTaskUseCase
import io.task.tracker.core.port.output.TaskRepository
import org.springframework.stereotype.Service
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