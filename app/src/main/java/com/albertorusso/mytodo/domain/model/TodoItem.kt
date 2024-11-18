package com.albertorusso.mytodo.domain.model

data class TodoItem(val id: Int, val title: String, val description: String, var isDone: Boolean)
