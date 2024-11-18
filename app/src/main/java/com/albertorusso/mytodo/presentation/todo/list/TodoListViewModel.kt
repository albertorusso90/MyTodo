package com.albertorusso.mytodo.presentation.todo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.domain.usecase.DeleteTodoUseCase
import com.albertorusso.mytodo.domain.usecase.GetTodosUseCase
import com.albertorusso.mytodo.domain.usecase.UpdateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {
    private val _todoItems = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoItems: StateFlow<List<TodoItem>> = _todoItems
    
    init {
        fetchTodos()
    }
    
    // Fetch the list of TODOs using GetTodosUseCase
    private fun fetchTodos() {
        viewModelScope.launch {
            getTodosUseCase().collect { todos ->
                _todoItems.value = todos
            }
        }
    }
    
    // Update the status of a TODO item using UpdateTodoUseCase
    fun updateTodoItemStatus(id: Int, isDone: Boolean) {
        val todoItem = _todoItems.value.find { it.id == id }
        if (todoItem != null) {
            viewModelScope.launch {
                val updatedTodo = todoItem.copy(isDone = isDone)
                updateTodoUseCase(updatedTodo)
                // Refresh the list after update
                fetchTodos()
            }
        }
    }
    
    fun deleteTodoItem(todoId: Int) {
        viewModelScope.launch {
            deleteTodoUseCase(todoId)
            fetchTodos() // Refresh the list after deletion
        }
    }
}
