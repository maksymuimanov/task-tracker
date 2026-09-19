package io.task.tracker.mapper

import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import io.task.tracker.core.port.input.CreateTaskCommand
import io.task.tracker.core.port.input.UpdateTaskCommand
import io.task.tracker.domain.Task
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import java.time.Instant
import java.util.*

@Mapper(componentModel = SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
interface TaskMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "info.title", source = "title")
    @Mapping(target = "info.description", source = "description")
    @Mapping(target = "state.priority", source = "priority")
    @Mapping(target = "state.status", source = "status")
    @Mapping(target = "attachments", source = "attachments")
    @Mapping(target = "subtasks", source = "subtasks")
    @Mapping(target = "metadata.parentId", source = "parentId")
    @Mapping(target = "metadata.createdAt", source = "createdAt")
    @Mapping(target = "metadata.updatedAt", ignore = true)
    fun toTask(id: UUID, command: CreateTaskCommand, createdAt: Instant): Task

    fun toTaskDocument(task: Task): TaskDocument

    fun toTask(taskDocument: TaskDocument): Task

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "info.title", source = "title")
    @Mapping(target = "info.description", source = "description")
    @Mapping(target = "state.priority", source = "priority")
    @Mapping(target = "state.status", source = "status")
    @Mapping(target = "attachments", source = "attachments")
    @Mapping(target = "subtasks", source = "subtasks")
    @Mapping(target = "metadata.parentId", source = "parentId")
    @Mapping(target = "metadata.createdAt", ignore = true)
    @Mapping(target = "metadata.updatedAt", source = "updatedAt")
    fun updateTask(@MappingTarget task: Task, command: UpdateTaskCommand, updatedAt: Instant): Task
}