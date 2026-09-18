package io.task.tracker.adapter.output.mongodb

import io.task.tracker.adapter.output.mongodb.document.TaskDocument
import io.task.tracker.adapter.output.mongodb.repository.TaskDocumentRepository
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TaskMongoDBRepository(
    private val taskDocumentRepository: TaskDocumentRepository
) : TaskRepository {
    override fun saveTask(task: Task): Task {
        val taskDocument = TaskDocument.fromTask(task)
        return taskDocumentRepository.save(taskDocument).toTask()
    }

    override fun findTaskById(id: UUID): Task {
        return taskDocumentRepository.findById(id)
            .orElseThrow()
            .toTask()
    }

    override fun findAllHeadTasks(pageInfo: PageInfo): List<Task> {
        return taskDocumentRepository.findAllByMetadataParentIdIsNull()
            .map(TaskDocument::toTask)
    }

    override fun findAllTasksByParentId(
        parentId: UUID,
        pageInfo: PageInfo
    ): List<Task> {
        return taskDocumentRepository.findAllByMetadataParentId(parentId)
            .map(TaskDocument::toTask)
    }

    override fun deleteTaskById(id: UUID) {
        taskDocumentRepository.deleteById(id)
    }
}