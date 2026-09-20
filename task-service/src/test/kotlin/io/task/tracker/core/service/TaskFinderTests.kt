package io.task.tracker.core.service

import io.task.tracker.core.exception.NotFoundException
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.Task
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

@ExtendWith(MockitoExtension::class)
class TaskFinderTests {
    @InjectMocks
    private lateinit var taskFinder: TaskFinder
    @Mock
    private lateinit var taskRepository: TaskRepository

    @Test
    fun `findTaskById should find task by id`(): Unit = runTest {
        val id = UUID.randomUUID()
        val task = mock<Task>()

        whenever(taskRepository.findTaskById(id)).thenReturn(task)

        val foundTask = taskFinder.findTaskById(id)

        assertThat(foundTask).isEqualTo(task)
        verify(taskRepository).findTaskById(id)
    }

    @Test
    fun `findTaskById should throw NotFoundException when task is not found`(): Unit = runTest {
        val id = UUID.randomUUID()

        whenever(taskRepository.findTaskById(id)).thenReturn(null)

        assertThatCode { runBlocking { taskFinder.findTaskById(id) } }
            .isInstanceOf(NotFoundException::class.java)
        verify(taskRepository).findTaskById(id)
    }
}