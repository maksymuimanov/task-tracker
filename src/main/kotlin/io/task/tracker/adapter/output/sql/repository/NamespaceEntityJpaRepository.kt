package io.task.tracker.adapter.output.sql.repository

import io.task.tracker.adapter.output.sql.TaskEntity
import io.task.tracker.adapter.output.sql.entity.NamespaceEntity
import io.task.tracker.domain.Namespace
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NamespaceEntityJpaRepository : JpaRepository<NamespaceEntity, UUID> {
}