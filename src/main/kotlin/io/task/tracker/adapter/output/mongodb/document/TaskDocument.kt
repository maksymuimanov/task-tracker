package io.task.tracker.adapter.output.mongodb.document

import io.task.tracker.domain.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "tasks")
data class TaskDocument(
    @Id
    val id: UUID,
    var info: TaskInfo,
    var state: TaskState,
    var attachments: List<TaskAttachment>,
    var subtasks: List<TaskDocument>,
    var metadata: TaskMetadata
) {
    fun toTask() : Task =
        Task(id, info, state, attachments, subtasks.map(TaskDocument::toTask), metadata)

    companion object {
        fun fromTask(task: Task) : TaskDocument =
            with(task) {
                TaskDocument(id, info, state, attachments, subtasks.map(TaskDocument::fromTask), metadata)
            }
    }
}