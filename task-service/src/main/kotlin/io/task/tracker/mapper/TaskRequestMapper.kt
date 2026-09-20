package io.task.tracker.mapper

import io.task.tracker.adapter.input.rest.CreateTaskRequest
import io.task.tracker.adapter.input.rest.UpdateTaskRequest
import io.task.tracker.core.port.input.CreateTaskCommand
import io.task.tracker.core.port.input.UpdateTaskCommand
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    componentModel = SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface TaskRequestMapper {
    fun toCreateTaskCommand(request: CreateTaskRequest): CreateTaskCommand

    fun toUpdateTaskCommand(request: UpdateTaskRequest): UpdateTaskCommand
}