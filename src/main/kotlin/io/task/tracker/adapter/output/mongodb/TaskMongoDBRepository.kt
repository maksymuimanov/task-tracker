package io.task.tracker.adapter.output.mongodb

import io.task.tracker.adapter.output.mongodb.repository.TaskDocumentRepository
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import io.task.tracker.mapper.TaskMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TaskMongoDBRepository(
    private val taskMapper: TaskMapper,
    private val taskDocumentRepository: TaskDocumentRepository
) : TaskRepository {
    override fun saveTask(task: Task): Task {
        val taskDocument = taskMapper.toTaskDocument(task)
        val savedTask = taskDocumentRepository.save(taskDocument)
        return taskMapper.toTask(savedTask)
    }

    override fun findTaskById(id: UUID): Task {
        val taskDocument = taskDocumentRepository.findById(id)
            .orElseThrow()
        return taskMapper.toTask(taskDocument)
    }

    override fun findAllHeadTasks(pageInfo: PageInfo): List<Task> {
        return taskDocumentRepository.findAllByMetadataParentIdIsNull()
            .map { taskMapper.toTask(it) }
    }

    override fun findAllTasksByParentId(
        parentId: UUID,
        pageInfo: PageInfo
    ): List<Task> {
        return taskDocumentRepository.findAllByMetadataParentId(parentId)
            .map { taskMapper.toTask(it) }
    }

    override fun deleteTaskById(id: UUID) {
        taskDocumentRepository.deleteById(id)
    }
}