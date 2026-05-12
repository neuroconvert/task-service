package com.neuroconvert.taskservice.controller

import com.neuroconvert.taskservice.model.Task
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TaskControllerTest {

    private val controller = TaskController()

    @Test
    fun `should return all tasks`() {
        val result = controller.getAllTasks()
        assertTrue(result.isNotEmpty())
        assertEquals(3, result.size)
    }

    @Test
    fun `should return task by id`() {
        val response = controller.getTaskById(1L)
        assertEquals(200, response.statusCode.value())
        assertEquals("Setup CI pipeline", response.body?.title)
    }

    @Test
    fun `should return 404 for unknown id`() {
        val response = controller.getTaskById(999L)
        assertEquals(404, response.statusCode.value())
    }

    @Test
    fun `should create a new task`() {
        val newTask = Task(4, "New task")
        val response = controller.createTask(newTask)
        assertEquals(201, response.statusCode.value())
        assertEquals("New task", response.body?.title)
    }
}