package com.albertorusso.mytodo.presentation.todo.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertorusso.mytodo.domain.usecase.CreateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoCreateViewModel @Inject constructor(
    private val createTodoUseCase: CreateTodoUseCase
) : ViewModel() {
    
    fun createTodo(title: String, description: String) {
        viewModelScope.launch {
            createTodoUseCase(title, description)
        }
    }
}
