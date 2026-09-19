package io.task.tracker.mapper

import io.task.tracker.adapter.input.rest.CreateTaskRequest
import io.task.tracker.adapter.input.rest.TaskResponse
import io.task.tracker.adapter.input.rest.UpdateTaskRequest
import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import io.task.tracker.core.port.input.CreateTaskCommand
import io.task.tracker.core.port.input.UpdateTaskCommand
import io.task.tracker.domain.Task
import io.task.tracker.domain.TaskInfo
import io.task.tracker.domain.TaskMetadata
import io.task.tracker.domain.TaskState
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import java.time.Instant
import java.util.*

@Mapper(
    componentModel = SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface TaskMapper {
    fun toCreateTaskCommand(request: CreateTaskRequest): CreateTaskCommand

    fun toUpdateTaskCommand(request: UpdateTaskRequest): UpdateTaskCommand

    @Mapping(target = "id", source = "id")
    @Mapping(target = "info", source = "command")
    @Mapping(target = "state", source = "command")
    @Mapping(target = "metadata", expression = "java(toTaskMetadata(command, createdAt))")
    @Mapping(target = "attachments", source = "command.attachments")
    @Mapping(target = "subtasks", source = "command.subtasks")
    fun toTask(id: UUID, command: CreateTaskCommand, createdAt: Instant): Task

    @Mapping(target = "title", source = "command.title")
    @Mapping(target = "description", source = "command.description")
    fun toTaskInfo(command: CreateTaskCommand): TaskInfo

    @Mapping(target = "priority", source = "command.priority")
    @Mapping(target = "status", source = "command.status")
    fun toTaskState(command: CreateTaskCommand): TaskState

    @Mapping(target = "parentId", source = "command.parentId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "createdAt")
    fun toTaskMetadata(command: CreateTaskCommand, createdAt: Instant): TaskMetadata

    fun toTaskDocument(task: Task): TaskDocument

    fun toTask(taskDocument: TaskDocument): Task

    fun updateTask(@MappingTarget task: Task, command: UpdateTaskCommand, updatedAt: Instant): Task {
        updateTaskInfo(task.info, command)
        updateTaskState(task.state, command)
        updateTaskMetadata(task.metadata, command, updatedAt)
        return task
    }

    @Mapping(target = "title", source = "command.title")
    @Mapping(target = "description", source = "command.description")
    fun updateTaskInfo(@MappingTarget info: TaskInfo, command: UpdateTaskCommand): TaskInfo

    @Mapping(target = "priority", source = "command.priority")
    @Mapping(target = "status", source = "command.status")
    fun updateTaskState(@MappingTarget state: TaskState, command: UpdateTaskCommand): TaskState

    @Mapping(target = "parentId", source = "command.parentId")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "createdAt", ignore = true)
    fun updateTaskMetadata(@MappingTarget metadata: TaskMetadata, command: UpdateTaskCommand, updatedAt: Instant): TaskMetadata

    fun toTaskResponse(task: Task): TaskResponse
}