package io.task.tracker.domain

import java.util.UUID

data class Namespace(
    val id: UUID,
    val name: String
)