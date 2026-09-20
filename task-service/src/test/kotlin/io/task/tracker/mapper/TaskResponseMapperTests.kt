package io.task.tracker.mapper

import io.task.tracker.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.net.URI
import java.time.Instant
import java.util.*

class TaskResponseMapperTests {
    private val taskResponseMapper = Mappers.getMapper(TaskResponseMapper::class.java)

    @Test
    fun `toTaskResponse should map task to response`() {
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

        val response = taskResponseMapper.toTaskResponse(task)

        assertThat(response.id).isEqualTo(task.id)
        assertThat(response.info).isEqualTo(task.info)
        assertThat(response.state).isEqualTo(task.state)
        assertThat(response.attachments).isEqualTo(task.attachments)
        assertThat(response.metadata).isEqualTo(task.metadata)
    }
}