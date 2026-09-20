package io.task.tracker.mapper

import io.task.tracker.adapter.input.rest.TaskResponse
import io.task.tracker.domain.Task
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    componentModel = SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface TaskResponseMapper {
    fun toTaskResponse(task: Task): TaskResponse
}