package io.task.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskTrackerApplication

fun main(args: Array<String>) {
    val context = runApplication<TaskTrackerApplication>(*args)

}
