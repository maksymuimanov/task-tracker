package io.task.tracker.domain

data class TaskState(
    var priority: TaskPriority,
    var status: TaskStatus
)
