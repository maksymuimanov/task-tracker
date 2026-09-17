package io.tracker.task.adapter.input.rest

import io.tracker.task.core.port.input.CreateTaskCommand
import io.tracker.task.core.port.input.CreateTaskUseCase
import io.tracker.task.core.port.input.DeleteTaskUseCase
import io.tracker.task.core.port.input.FindTaskUseCase
import io.tracker.task.core.port.input.UpdateTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/{version}/tasks", version = "1")
class TaskController(
    private val createTaskUseCase: CreateTaskUseCase,
    private val findTaskUseCase: FindTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) {
    @PostMapping
    fun createTask(@RequestBody request: CreateTaskRequest): ResponseEntity<TaskResponse> {
        val command = CreateTaskCommand(request.title, request.description, request.priority, request.status, request.progress, request.attachments, request.subtasks, request.userId, request.projectId, request.parentId)
        val task = createTaskUseCase.createTask(command)
        return
    }
}