package io.task.tracker.domain

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
