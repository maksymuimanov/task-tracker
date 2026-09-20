package io.task.tracker.mapper

import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import io.task.tracker.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.net.URI
import java.time.Instant
import java.util.*

class TaskDocumentMapperTests {
    private val taskDocumentMapper = Mappers.getMapper(TaskDocumentMapper::class.java)
    
    @Test
    fun `toTask should map document to task`() {
        val document = TaskDocument(
            id = UUID.randomUUID(),
            info = TaskInfo(
                title = "title",
                description = "description"
            ),
            state = TaskState(
                priority = TaskPriority.MEDIUM,
                status = TaskStatus.IN_PROGRESS
            ),
            attachments = listOf(
                TaskAttachment.Base64("base64"),
                TaskAttachment.Url(URI.create("http://localhost:8080"))
            ),
            metadata = TaskMetadata(
                namespaceId = UUID.randomUUID(),
                parentId = null,
                createdAt = Instant.now(),
                updatedAt = Instant.now()
            )
        )

        val task = taskDocumentMapper.toTask(document)

        assertThat(task.id).isEqualTo(document.id)
        assertThat(task.info).isEqualTo(document.info)
        assertThat(task.state).isEqualTo(document.state)
        assertThat(task.attachments).isEqualTo(document.attachments)
        assertThat(task.metadata).isEqualTo(document.metadata)
    }

    @Test
    fun `toTaskDocument should map task to document`() {
        val task = Task(
            id = UUID.randomUUID(),
            info = TaskInfo(
                title = "title",
                description = "description"
            ),
            state = TaskState(
                priority = TaskPriority.MEDIUM,
                status = TaskStatus.IN_PROGRESS
            ),
            attachments = listOf(
                TaskAttachment.Base64("base64"),
                TaskAttachment.Url(URI.create("http://localhost:8080"))
            ),
            metadata = TaskMetadata(
                namespaceId = UUID.randomUUID(),
                parentId = null,
                createdAt = Instant.now(),
                updatedAt = Instant.now()
            )
        )

        val document = taskDocumentMapper.toTaskDocument(task)

        assertThat(document.id).isEqualTo(task.id)
        assertThat(document.info).isEqualTo(task.info)
        assertThat(document.state).isEqualTo(task.state)
        assertThat(document.attachments).isEqualTo(task.attachments)
        assertThat(document.metadata).isEqualTo(task.metadata)
    }
}