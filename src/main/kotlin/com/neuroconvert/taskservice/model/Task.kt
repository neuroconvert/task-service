package com.neuroconvert.taskservice.model

data class Task(
    val id: Long,
    val title: String,
    val completed: Boolean = false
)