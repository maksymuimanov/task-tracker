package io.tracker.task.domain

data class TaskState(
    var priority: TaskPriority,
    var status: TaskStatus,
    var progress: TaskProgress
)
