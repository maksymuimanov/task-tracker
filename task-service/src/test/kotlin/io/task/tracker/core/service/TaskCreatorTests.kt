package io.task.tracker.core.service

import io.task.tracker.core.port.input.CreateTaskCommand
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.Task
import io.task.tracker.mapper.TaskMapper
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Clock
import java.time.Instant
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class TaskCreatorTests {
    @InjectMocks
    private lateinit var taskCreator: TaskCreator
    @Mock
    private lateinit var taskMapper: TaskMapper
    @Mock
    private lateinit var taskRepository: TaskRepository
    @Mock
    private lateinit var clock: Clock

    @Test
    fun `createTask should create task`(): Unit = runTest {
        val command = mock<CreateTaskCommand>()
        val createdAt = mock<Instant>()
        val task = mock<Task>()

        whenever(clock.instant()).thenReturn(createdAt)
        whenever(taskMapper.toTask(any(), any(), any())).thenReturn(task)
        whenever(taskRepository.saveTask(task)).thenReturn(task)

        val createdTask = taskCreator.createTask(command)

        assertThat(createdTask).isEqualTo(task)
        verify(clock).instant()
        verify(taskMapper).toTask(any(), any(), any())
        verify(taskRepository).saveTask(task)
    }
}