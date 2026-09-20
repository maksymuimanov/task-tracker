package io.task.tracker.adapter.output.mongodb

import io.task.tracker.adapter.output.mongodb.repository.TaskDocumentRepository
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import io.task.tracker.mapper.TaskDocumentMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TaskMongoDBRepository(
    private val taskDocumentMapper: TaskDocumentMapper,
    private val taskDocumentRepository: TaskDocumentRepository
) : TaskRepository {
    override suspend fun saveTask(task: Task): Task {
        val taskDocument = taskDocumentMapper.toTaskDocument(task)
        val savedTask = taskDocumentRepository.save(taskDocument)
        return taskDocumentMapper.toTask(savedTask)
    }

    override suspend fun findTaskById(id: UUID): Task? {
        val taskDocument = taskDocumentRepository.findById(id)
        return taskDocumentMapper.toTask(taskDocument)
    }

    override fun findAllHeadTasks(namespaceId: UUID, pageInfo: PageInfo): Flow<Task> {
        return taskDocumentRepository.findAllByMetadataNamespaceIdAndMetadataParentIdIsNull(namespaceId)
            .map { taskDocumentMapper.toTask(it) }
    }

    override fun findAllTasksByParentId(
        parentId: UUID,
        pageInfo: PageInfo
    ): Flow<Task> {
        return taskDocumentRepository.findAllByMetadataParentId(parentId)
            .map { taskDocumentMapper.toTask(it) }
    }

    override suspend fun deleteTaskById(id: UUID) {
        taskDocumentRepository.deleteById(id)
    }
}