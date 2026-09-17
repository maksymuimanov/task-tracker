package io.task.tracker.adapter.output.sql

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "base64_task_attachments")
class Base64TaskAttachmentEntity(
    @Id
    var id: UUID,
    var content: String,
    @ManyToOne
    @JoinColumn(name = "task_id")
    var task: TaskEntity
)