package com.albertorusso.mytodo.domain.usecase

import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.domain.repository.TodoRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateTodoUseCaseTest {
    
    private val mockRepository: TodoRepository = mockk(relaxed = true)
    private val updateTodoUseCase = UpdateTodoUseCase(mockRepository)
    
    @Test
    fun `should update todo item in repository`() = runTest {
        val todo = TodoItem(1, "Updated Title", "Updated Description", true)
        
        updateTodoUseCase(todo)
        
        coVerify { mockRepository.updateTodo(todo) }
    }
    
    @Test
    fun `should handle exception from repository`() = runTest {
        val todo = TodoItem(1, "Updated Title", "Updated Description", true)
        coEvery { mockRepository.updateTodo(todo) } throws RuntimeException("Database error")
        
        try {
            updateTodoUseCase(todo)
            assert(false) { "Exception was not thrown" }
        } catch (e: RuntimeException) {
            assert(e.message == "Database error")
        }
        
        coVerify { mockRepository.updateTodo(todo) }
    }
}
