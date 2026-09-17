package io.task.tracker.adapter.output.sql.repository

import io.task.tracker.adapter.output.sql.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TaskEntityJpaRepository : JpaRepository<TaskEntity, UUID> {
}