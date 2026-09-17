package io.tracker.task.domain

import java.util.UUID

data class TaskInfo(
    var title: String,
    var description: String
) {
    init {
        require(title.isNotBlank()) {
            "Title cannot be blank"
        }
    }
}
