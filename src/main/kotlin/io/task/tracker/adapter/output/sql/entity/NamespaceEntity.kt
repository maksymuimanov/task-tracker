package io.task.tracker.adapter.output.sql.entity

import io.task.tracker.adapter.output.sql.TaskEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "namespaces")
class NamespaceEntity(
    @Id
    var id: UUID,
    var name: String,
    @OneToMany(mappedBy = "namespace")
    var tasks: MutableList<TaskEntity>,
)