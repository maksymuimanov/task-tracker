package io.tracker.task.domain

private const val PERCENTAGE_BASE = 100

data class TaskProgress(
    var completed: Int,
) {
    init {
        require(completed <= PERCENTAGE_BASE) {
            "Completed must be less than or equal to total"
        }
    }
}