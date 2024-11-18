package com.albertorusso.mytodo.domain.usecase

import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.domain.repository.TodoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTodosUseCaseTest {
    
    private lateinit var getTodosUseCase: GetTodosUseCase
    private val mockRepository: TodoRepository = mockk()
    
    @Before
    fun setUp() {
        getTodosUseCase = GetTodosUseCase(mockRepository)
    }
    
    @Test
    fun `should return all todos from repository`() = runTest {
        val todos = listOf(
            TodoItem(1, "Test Todo 1", "Test Description 1", false),
            TodoItem(2, "Test Todo 2", "Test Description 2", true)
        )
        coEvery { mockRepository.getTodos() } returns flowOf(todos)
        
        val result = getTodosUseCase().first()
        
        // Assert
        assertEquals(todos, result)
    }
    
    @Test
    fun `should return empty list if no todos are present`() = runTest {
        // Arrange
        coEvery { mockRepository.getTodos() } returns flowOf(emptyList())
        
        // Act
        val result = getTodosUseCase().first()
        
        // Assert
        assertEquals(emptyList<TodoItem>(), result)
    }
}
