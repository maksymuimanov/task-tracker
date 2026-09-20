package io.task.tracker.mapper

import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import io.task.tracker.domain.Task
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    componentModel = SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface TaskDocumentMapper {
    fun toTask(taskDocument: TaskDocument?): Task

    fun toTaskDocument(task: Task): TaskDocument
}