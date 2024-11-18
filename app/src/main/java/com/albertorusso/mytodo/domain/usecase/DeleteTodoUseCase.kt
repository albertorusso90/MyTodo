package com.albertorusso.mytodo.domain.usecase

import com.albertorusso.mytodo.domain.repository.TodoRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todoId: Int) {
        repository.deleteTodo(todoId)
    }
}
