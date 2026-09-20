package io.task.tracker.mapper

import io.task.tracker.adapter.input.rest.CreateTaskRequest
import io.task.tracker.adapter.input.rest.UpdateTaskRequest
import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import io.task.tracker.core.port.input.CreateTaskCommand
import io.task.tracker.core.port.input.UpdateTaskCommand
import io.task.tracker.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.net.URI
import java.time.Instant
import java.util.*

class TaskMapperTests {
    private val taskMapper = Mappers.getMapper(TaskMapper::class.java)

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

        val command = taskMapper.toCreateTaskCommand(request)

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

        val command = taskMapper.toUpdateTaskCommand(request)

        assertThat(command.title).isEqualTo(request.title)
        assertThat(command.description).isEqualTo(request.description)
        assertThat(command.priority).isEqualTo(request.priority)
        assertThat(command.status).isEqualTo(request.status)
        assertThat(command.attachments).isEqualTo(request.attachments)
        assertThat(command.namespaceId).isEqualTo(request.namespaceId)
        assertThat(command.parentId).isEqualTo(request.parentId)
    }

    @Test
    fun `toTask should map command to task`() {
        val id = UUID.randomUUID()
        val command = CreateTaskCommand(
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
        val createdAt = Instant.now()

        val task = taskMapper.toTask(id, command, createdAt)

        assertThat(task.id).isEqualTo(id)
        assertThat(task.info.title).isEqualTo(command.title)
        assertThat(task.info.description).isEqualTo(command.description)
        assertThat(task.state.priority).isEqualTo(command.priority)
        assertThat(task.state.status).isEqualTo(command.status)
        assertThat(task.attachments).isEqualTo(command.attachments)
        assertThat(task.metadata.namespaceId).isEqualTo(command.namespaceId)
        assertThat(task.metadata.parentId).isEqualTo(command.parentId)
        assertThat(task.metadata.createdAt).isEqualTo(createdAt)
        assertThat(task.metadata.updatedAt).isEqualTo(createdAt)
    }

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

        val task = taskMapper.toTask(document)

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

        val document = taskMapper.toTaskDocument(task)

        assertThat(document.id).isEqualTo(task.id)
        assertThat(document.info).isEqualTo(task.info)
        assertThat(document.state).isEqualTo(task.state)
        assertThat(document.attachments).isEqualTo(task.attachments)
        assertThat(document.metadata).isEqualTo(task.metadata)
    }

    @Test
    fun `updateTask should partially update task`() {
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
        val command = UpdateTaskCommand(
            title = "new title",
            description = null,
            priority = null,
            status = TaskStatus.ON_HOLD,
            attachments = listOf(
                TaskAttachment.Base64("base64")
            ),
            namespaceId = null,
            parentId = null
        )
        val updatedAt = Instant.now()

        val updatedTask = taskMapper.updateTask(task, command, updatedAt)

        assertThat(updatedTask.id).isEqualTo(task.id)
        assertThat(updatedTask.info.title).isEqualTo(command.title)
        assertThat(updatedTask.info.description).isEqualTo(task.info.description)
        assertThat(updatedTask.state.priority).isEqualTo(task.state.priority)
        assertThat(updatedTask.state.status).isEqualTo(command.status)
        assertThat(updatedTask.attachments).isEqualTo(command.attachments)
        assertThat(updatedTask.metadata.namespaceId).isEqualTo(task.metadata.namespaceId)
        assertThat(updatedTask.metadata.parentId).isEqualTo(task.metadata.parentId)
        assertThat(updatedTask.metadata.createdAt).isEqualTo(task.metadata.createdAt)
        assertThat(updatedTask.metadata.updatedAt).isEqualTo(updatedAt)
    }
}