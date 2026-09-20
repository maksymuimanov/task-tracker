package io.task.tracker.config

import io.task.tracker.core.port.output.TaskRepository
import io.task.tracker.core.service.TaskCreator
import io.task.tracker.core.service.TaskDeleter
import io.task.tracker.core.service.TaskFinder
import io.task.tracker.core.service.TaskUpdater
import io.task.tracker.mapper.TaskMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class TaskConfig {
    @Bean
    fun taskCreator(taskMapper: TaskMapper, taskRepository: TaskRepository, clock: Clock) =
        TaskCreator(taskMapper, taskRepository, clock)

    @Bean
    fun taskFinder(taskRepository: TaskRepository) =
        TaskFinder(taskRepository)

    @Bean
    fun taskUpdater(taskMapper: TaskMapper, taskRepository: TaskRepository, clock: Clock) =
        TaskUpdater(taskMapper, taskRepository, clock)

    @Bean
    fun taskDeleter(taskRepository: TaskRepository) =
        TaskDeleter(taskRepository)
}