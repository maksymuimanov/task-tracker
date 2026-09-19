package io.task.tracker.adapter.input.rest

import io.task.tracker.core.service.TaskCreator
import io.task.tracker.core.service.TaskDeleter
import io.task.tracker.core.service.TaskFinder
import io.task.tracker.core.service.TaskUpdater
import io.task.tracker.domain.PageInfo
import io.task.tracker.mapper.TaskMapper
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/{version}/tasks")
class TaskController(
    private val taskMapper: TaskMapper,
    private val taskCreator: TaskCreator,
    private val taskFinder: TaskFinder,
    private val taskUpdater: TaskUpdater,
    private val taskDeleter: TaskDeleter
) {
    @PostMapping
    suspend fun createTask(
        @RequestBody @Valid request: CreateTaskRequest
    ): ResponseEntity<TaskResponse> {
        val command = taskMapper.toCreateTaskCommand(request)
        val task = taskCreator.createTask(command)
        val response = taskMapper.toTaskResponse(task)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping(params = ["page", "size", "parentId"])
    fun findTasks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(required = false) parentId: UUID?
    ): Flow<TaskResponse> {
        val pageInfo = PageInfo(page, size)
        return (parentId?.let { taskFinder.findAllTasksByParentId(it, pageInfo) } ?: taskFinder.findAllHeadTasks(pageInfo))
            .map { taskMapper.toTaskResponse(it) }
    }

    @GetMapping("/{id}")
    suspend fun findTaskById(
        @PathVariable id: UUID
    ): ResponseEntity<TaskResponse> {
        val task = taskFinder.findTaskById(id)
        val response = taskMapper.toTaskResponse(task)
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    suspend fun updateTask(
        @PathVariable id: UUID,
        @RequestBody @Valid request: UpdateTaskRequest
    ): ResponseEntity<TaskResponse> {
        val command = taskMapper.toUpdateTaskCommand(request)
        val task = taskUpdater.updateTask(id, command)
        val response = taskMapper.toTaskResponse(task)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    suspend fun deleteTask(
        @PathVariable id: UUID
    ): ResponseEntity<Unit> {
        taskDeleter.deleteTaskById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}