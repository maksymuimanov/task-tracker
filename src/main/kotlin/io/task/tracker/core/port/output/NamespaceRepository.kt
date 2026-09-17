package io.task.tracker.core.port.output

import io.task.tracker.domain.Namespace
import io.task.tracker.domain.PageInfo
import io.task.tracker.domain.Task
import java.util.UUID

interface NamespaceRepository {
    fun saveNamespace(namespace: Namespace): Namespace

    fun findNamespaceById(id: UUID): Namespace

    fun findAllNamespaces(pageInfo: PageInfo): List<Namespace>

    fun deleteNamespaceById(id: UUID)
}