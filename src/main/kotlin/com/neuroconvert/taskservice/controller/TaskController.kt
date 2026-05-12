package com.neuroconvert.taskservice.controller

import com.neuroconvert.taskservice.model.Task
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController {

    private val tasks = mutableListOf(
        Task(1, "Setup CI pipeline"),
        Task(2, "Write unit tests"),
        Task(3, "Configure SonarQube")
    )

    @GetMapping
    fun getAllTasks(): List<Task> = tasks

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = tasks.find { it.id == id }
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(task)
    }

    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        tasks.add(task)
        return ResponseEntity.status(201).body(task)
    }
}