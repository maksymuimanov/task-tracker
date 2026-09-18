package io.task.tracker.adapter.output.mongodb.repository

import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface TaskDocumentRepository : MongoRepository<TaskDocument, UUID> {
    fun findAllByMetadataParentIdIsNull(): List<TaskDocument>
    fun findAllByMetadataParentId(parentId: UUID): List<TaskDocument>
}
