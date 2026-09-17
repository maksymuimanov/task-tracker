package io.task.tracker.adapter.output.sql

import io.task.tracker.core.port.output.NamespaceRepository
import io.task.tracker.domain.Namespace
import io.task.tracker.domain.PageInfo
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class SqlNamespaceRepository : NamespaceRepository {
    override fun saveNamespace(namespace: Namespace): Namespace {
        TODO("Not yet implemented")
    }

    override fun findNamespaceById(id: UUID): Namespace {
        TODO("Not yet implemented")
    }

    override fun findAllNamespaces(pageInfo: PageInfo): List<Namespace> {
        TODO("Not yet implemented")
    }

    override fun deleteNamespaceById(id: UUID) {
        TODO("Not yet implemented")
    }
}