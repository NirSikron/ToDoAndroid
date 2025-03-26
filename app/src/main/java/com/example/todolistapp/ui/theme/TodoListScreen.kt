package com.example.todolistapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete


import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolistapp.model.TaskItem

@Composable
fun TodoListScreen() {
    var taskText by remember { mutableStateOf("") }
    val taskList = remember { mutableStateListOf<TaskItem>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = taskText,
            onValueChange = { taskText = it },
            label = { Text("הכנס משימה") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (taskText.isNotBlank()) {
                    taskList.add(TaskItem(taskText))
                    taskText = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("הוסף משימה")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(taskList) { task ->
                TaskRow(task = task, onDelete = { taskList.remove(task) })
            }
        }
    }
}

@Composable
fun TaskRow(task: TaskItem, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { task.isDone = it }
            )

            Text(
                text = task.title,
                style = if (task.isDone)
                    MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        textDecoration = TextDecoration.LineThrough
                    )
                else MaterialTheme.typography.bodyLarge
            )
        }

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "מחק משימה"
            )
        }
    }
}
