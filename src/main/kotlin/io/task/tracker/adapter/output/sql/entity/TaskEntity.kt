package io.task.tracker.adapter.output.sql

import io.task.tracker.adapter.output.sql.entity.NamespaceEntity
import io.task.tracker.domain.TaskPriority
import io.task.tracker.domain.TaskStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tasks")
class TaskEntity (
    @Id
    var id: UUID,
    var title: String,
    var description: String,
    @Enumerated(EnumType.STRING)
    var priority: TaskPriority,
    @Enumerated(EnumType.STRING)
    var status: TaskStatus,
    var completed: Int,
    @OneToMany(mappedBy = "task")
    var base64Attachments: MutableList<Base64TaskAttachmentEntity>,
    @OneToMany(mappedBy = "task")
    var urlAttachments: MutableList<UrlTaskAttachmentEntity>,
    @OneToMany(mappedBy = "parent")
    var subtasks: MutableList<TaskEntity>,
    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parent: TaskEntity?,
    @ManyToOne
    @JoinColumn(name = "namespace_id")
    var namespace: NamespaceEntity
)