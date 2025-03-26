package com.example.todolistapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TaskItem(title: String, isDone: Boolean = false) {
    var title by mutableStateOf(title)
    var isDone by mutableStateOf(isDone)
}
