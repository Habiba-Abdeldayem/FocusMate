package com.example.leveluptasks.data.source

import com.example.leveluptasks.data.model.Task

object DataSource {
   val fakeTasks = listOf(
       Task(name = "Buy groceries", isDone = false),
       Task(name = "Finish Android assignment", isDone = true),
       Task(name = "Read 10 pages of book", isDone = false),
//       Task(name = "Go for a walk", isDone = true),
//       Task(name = "Clean the desk", isDone = false),
//       Task(name = "Water the plants", isDone = false),
//       Task(name = "Reply to emails", isDone = true),
//       Task(name = "Plan weekend trip", isDone = false)
    )
}