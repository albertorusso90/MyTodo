package com.albertorusso.mytodo.presentation.todo.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.domain.usecase.DeleteTodoUseCase
import com.albertorusso.mytodo.domain.usecase.GetTodoByIdUseCase
import com.albertorusso.mytodo.domain.usecase.UpdateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailsViewModel @Inject constructor(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    savedStateHandle: SavedStateHandle // Used to retrieve arguments passed via navigation
) : ViewModel() {
    
    private val _todoItem = MutableStateFlow<TodoItem?>(null)
    val todoItem: StateFlow<TodoItem?> = _todoItem
    
    init {
        val todoIdString = savedStateHandle.get<String>("todoId")
        val todoId = todoIdString?.toIntOrNull() // Safely convert to Int
        if (todoId != null) {
            fetchTodoItem(todoId)
        } else {
            // Handle invalid todoId (e.g., log error or handle gracefully)
        }
    }
    
    private fun fetchTodoItem(todoId: Int) {
        viewModelScope.launch {
            getTodoByIdUseCase(todoId).collect { item ->
                _todoItem.value = item
            }
        }
    }
    
    fun updateTodoItem(title: String, description: String) {
        val currentItem = _todoItem.value
        if (currentItem != null) {
            viewModelScope.launch {
                val updatedTodo = currentItem.copy(title = title, description = description)
                updateTodoUseCase(updatedTodo)
                fetchTodoItem(currentItem.id) // Refresh the item after update
            }
        }
    }
    
    fun deleteTodoItem() {
        val currentItem = _todoItem.value
        if (currentItem != null) {
            viewModelScope.launch {
                deleteTodoUseCase(currentItem.id)
            }
        }
    }
}
