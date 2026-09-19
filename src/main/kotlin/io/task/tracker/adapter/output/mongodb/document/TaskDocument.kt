package io.task.tracker.adapter.output.mongodb.document

import io.task.tracker.domain.TaskAttachment
import io.task.tracker.domain.TaskInfo
import io.task.tracker.domain.TaskMetadata
import io.task.tracker.domain.TaskState
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
)