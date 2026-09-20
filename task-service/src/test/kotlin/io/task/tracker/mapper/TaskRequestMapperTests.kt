package io.task.tracker.mapper

import io.task.tracker.adapter.input.rest.CreateTaskRequest
import io.task.tracker.adapter.input.rest.UpdateTaskRequest
import io.task.tracker.domain.TaskAttachment
import io.task.tracker.domain.TaskPriority
import io.task.tracker.domain.TaskStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.net.URI
import java.util.*

class TaskRequestMapperTests {
    private val taskRequestMapper = Mappers.getMapper(TaskRequestMapper::class.java)

    @Test
    fun `toCreateTaskCommand should map request to command`() {
        val request = CreateTaskRequest(
            title = "title",
            description = "description",
            priority = TaskPriority.LOW,
            status = TaskStatus.NOT_STARTED,
            attachments = listOf(
                TaskAttachment.Base64("base64"),
                TaskAttachment.Url(URI.create("http://localhost:8080"))
            ),
            namespaceId = UUID.randomUUID(),
            parentId = null
        )

        val command = taskRequestMapper.toCreateTaskCommand(request)

        assertThat(command.title).isEqualTo(request.title)
        assertThat(command.description).isEqualTo(request.description)
        assertThat(command.priority).isEqualTo(request.priority)
        assertThat(command.status).isEqualTo(request.status)
        assertThat(command.attachments).isEqualTo(request.attachments)
        assertThat(command.namespaceId).isEqualTo(request.namespaceId)
        assertThat(command.parentId).isEqualTo(request.parentId)
    }

    @Test
    fun `toUpdateTaskCommand should map request to command`() {
        val request = UpdateTaskRequest(
            title = "title",
            description = null,
            priority = null,
            status = TaskStatus.ON_HOLD,
            attachments = listOf(
                TaskAttachment.Base64("base64")
            ),
            namespaceId = null,
            parentId = null
        )

        val command = taskRequestMapper.toUpdateTaskCommand(request)

        assertThat(command.title).isEqualTo(request.title)
        assertThat(command.description).isEqualTo(request.description)
        assertThat(command.priority).isEqualTo(request.priority)
        assertThat(command.status).isEqualTo(request.status)
        assertThat(command.attachments).isEqualTo(request.attachments)
        assertThat(command.namespaceId).isEqualTo(request.namespaceId)
        assertThat(command.parentId).isEqualTo(request.parentId)
    }
}