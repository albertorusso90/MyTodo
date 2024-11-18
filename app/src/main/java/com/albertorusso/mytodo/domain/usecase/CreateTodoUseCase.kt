package com.albertorusso.mytodo.domain.usecase

import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.domain.repository.TodoRepository
import javax.inject.Inject

class CreateTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(title: String, description: String) {
        // Generate a unique ID (e.g., based on the current timestamp)
        val uniqueId = System.currentTimeMillis().toInt()
        
        // Create a new TodoItem
        val newTodo = TodoItem(
            id = uniqueId,
            title = title,
            description = description,
            isDone = false // New TODOs are not done by default
        )
        
        // Save the new TodoItem to the repository
        repository.insertTodo(newTodo)
    }
}
