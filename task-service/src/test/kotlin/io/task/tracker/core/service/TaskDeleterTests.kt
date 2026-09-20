package io.task.tracker.core.service

import io.task.tracker.core.port.output.TaskRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import java.util.*
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class TaskDeleterTests {
    @InjectMocks
    private lateinit var taskDeleter: TaskDeleter
    @Mock
    private lateinit var taskRepository: TaskRepository

    @Test
    fun `deleteTaskById should delete task`(): Unit = runTest {
        val taskId = UUID.randomUUID()

        taskDeleter.deleteTaskById(taskId)

        verify(taskRepository).deleteTaskById(taskId)
    }
}