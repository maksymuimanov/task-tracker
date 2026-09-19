package io.task.tracker.adapter.output.mongodb

import io.task.tracker.adapter.output.mongodb.repository.TaskDocumentRepository
import io.task.tracker.core.exception.NotFoundException
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import io.task.tracker.mapper.TaskMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TaskMongoDBRepository(
    private val taskMapper: TaskMapper,
    private val taskDocumentRepository: TaskDocumentRepository
) : TaskRepository {
    override suspend fun saveTask(task: Task): Task {
        val taskDocument = taskMapper.toTaskDocument(task)
        val savedTask = taskDocumentRepository.save(taskDocument)
        return taskMapper.toTask(savedTask)
    }

    override suspend fun findTaskById(id: UUID): Task {
        val taskDocument = taskDocumentRepository.findById(id)
            ?: throw NotFoundException("Task not found with id: $id")
        return taskMapper.toTask(taskDocument)
    }

    override fun findAllHeadTasks(pageInfo: PageInfo): Flow<Task> {
        return taskDocumentRepository.findAllByMetadataParentIdIsNull()
            .map { taskMapper.toTask(it) }
    }

    override fun findAllTasksByParentId(
        parentId: UUID,
        pageInfo: PageInfo
    ): Flow<Task> {
        return taskDocumentRepository.findAllByMetadataParentId(parentId)
            .map { taskMapper.toTask(it) }
    }

    override suspend fun deleteTaskById(id: UUID) {
        taskDocumentRepository.deleteById(id)
    }
}