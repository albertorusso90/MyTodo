package com.albertorusso.mytodo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean
)
