package io.task.tracker.adapter.output.mongodb.repository

import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface TaskDocumentRepository : CoroutineCrudRepository<TaskDocument, UUID> {
    fun findAllByMetadataParentIdIsNull(): Flow<TaskDocument>

    fun findAllByMetadataParentId(parentId: UUID): Flow<TaskDocument>
}
