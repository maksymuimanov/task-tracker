package io.task.tracker.adapter.input.rest

import io.task.tracker.core.service.TaskCreator
import io.task.tracker.core.service.TaskDeleter
import io.task.tracker.core.service.TaskFinder
import io.task.tracker.core.service.TaskUpdater
import io.task.tracker.domain.PageInfo
import io.task.tracker.mapper.TaskRequestMapper
import io.task.tracker.mapper.TaskResponseMapper
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
    private val taskRequestMapper: TaskRequestMapper,
    private val taskResponseMapper: TaskResponseMapper,
    private val taskCreator: TaskCreator,
    private val taskFinder: TaskFinder,
    private val taskUpdater: TaskUpdater,
    private val taskDeleter: TaskDeleter
) {
    @PostMapping
    suspend fun createTask(
        @RequestBody @Valid request: CreateTaskRequest
    ): ResponseEntity<TaskResponse> {
        val command = taskRequestMapper.toCreateTaskCommand(request)
        val task = taskCreator.createTask(command)
        val response = taskResponseMapper.toTaskResponse(task)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping(params = ["page", "size", "namespaceId"])
    fun findAllHeadTasks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam namespaceId: UUID
    ): Flow<TaskResponse> {
        val pageInfo = PageInfo(page, size)
        return taskFinder.findAllHeadTasks(namespaceId, pageInfo)
            .map { taskResponseMapper.toTaskResponse(it) }
    }

    @GetMapping(params = ["page", "size", "parentId"])
    fun findAllTasksByParentId(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam namespaceId: UUID
    ): Flow<TaskResponse> {
        val pageInfo = PageInfo(page, size)
        return taskFinder.findAllTasksByParentId(namespaceId, pageInfo)
            .map { taskResponseMapper.toTaskResponse(it) }
    }

    @GetMapping("/{id}")
    suspend fun findTaskById(
        @PathVariable id: UUID
    ): ResponseEntity<TaskResponse> {
        val task = taskFinder.findTaskById(id)
        val response = taskResponseMapper.toTaskResponse(task)
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    suspend fun updateTask(
        @PathVariable id: UUID,
        @RequestBody @Valid request: UpdateTaskRequest
    ): ResponseEntity<TaskResponse> {
        val command = taskRequestMapper.toUpdateTaskCommand(request)
        val task = taskUpdater.updateTask(id, command)
        val response = taskResponseMapper.toTaskResponse(task)
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