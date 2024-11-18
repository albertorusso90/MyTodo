package com.albertorusso.mytodo.domain.usecase

import com.albertorusso.mytodo.domain.repository.TodoRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class DeleteTodoUseCaseTest {
    private lateinit var deleteTodoUseCase: DeleteTodoUseCase
    private val mockRepository: TodoRepository = mockk(relaxed = true)
    
    @Before
    fun setUp() {
        deleteTodoUseCase = DeleteTodoUseCase(mockRepository)
    }
    
    @Test
    fun `should call deleteTodoItem on repository with correct id`() = runTest {
        val todoId = 123
        
        deleteTodoUseCase(todoId)
        
        coVerify { mockRepository.deleteTodo(todoId) }
    }
    
    @Test
    fun `should propagate exception when repository fails`() = runTest {
        val todoId = 123
        val expectedMessage = "Database error"
        coEvery { mockRepository.deleteTodo(todoId) } throws RuntimeException(expectedMessage)
        
        try {
            deleteTodoUseCase(todoId)
            fail("Expected exception was not thrown")
        } catch (e: RuntimeException) {
            assertEquals(expectedMessage, e.message)
        }
        
        coVerify { mockRepository.deleteTodo(todoId) }
    }
}
