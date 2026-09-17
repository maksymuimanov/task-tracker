package io.task.tracker.adapter.output.sql

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.net.URL
import java.util.UUID

@Entity
@Table(name = "url_task_attachments")
class UrlTaskAttachmentEntity(
    @Id
    var id: UUID,
    var url: URL,
    @ManyToOne
    @JoinColumn(name = "task_id")
    var task: TaskEntity
)