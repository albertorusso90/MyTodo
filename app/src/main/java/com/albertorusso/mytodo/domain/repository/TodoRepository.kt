package com.albertorusso.mytodo.domain.repository

import com.albertorusso.mytodo.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    /**
     * Fetch all TODOs items as a Flow.
     */
    fun getTodos(): Flow<List<TodoItem>>
    
    /**
     * Add a new TODOs item to the database.
     */
    suspend fun insertTodo(todo: TodoItem)
    
    /**
     * Update an existing TODOs item in the database.
     */
    suspend fun updateTodo(todo: TodoItem)
    
    /**
     * Update the "isDone" status of a TODOs item in the database.
     */
    suspend fun updateTodoStatus(id: Int, isDone: Boolean)
    
    /**
     * Delete a TODOs item from the database.
     */
    suspend fun deleteTodo(todoId: Int)
}
