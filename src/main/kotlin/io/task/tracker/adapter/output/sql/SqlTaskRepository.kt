package io.task.tracker.adapter.output.sql

import io.task.tracker.adapter.output.sql.repository.TaskEntityJpaRepository
import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class SqlTaskRepository(
    private val taskEntityJpaRepository: TaskEntityJpaRepository
) : TaskRepository {
    override fun saveTask(task: Task): Task {
        TODO("Not yet implemented")
    }

    override fun findTaskById(id: UUID): Task {
        TODO("Not yet implemented")
    }

    override fun findAllParentTasks(pageInfo: PageInfo): List<Task> {
        TODO("Not yet implemented")
    }

    override fun findAllParentTasksByParentId(
        parentId: UUID?,
        pageInfo: PageInfo
    ): List<Task> {
        TODO("Not yet implemented")
    }

    override fun deleteTaskById(id: UUID) {
        TODO("Not yet implemented")
    }
}