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


class GetTodoByIdUseCaseTest {
    
    private lateinit var getTodoByIdUseCase: GetTodoByIdUseCase
    private val mockRepository: TodoRepository = mockk()
    
    @Before
    fun setUp() {
        getTodoByIdUseCase = GetTodoByIdUseCase(mockRepository)
    }
    
    @Test
    fun `should return correct todo item by id`() = runTest {
        // Arrange
        val todoId = 123
        val todoList = listOf(
            TodoItem(123, "Test Title 1", "Test Description 1", false),
            TodoItem(456, "Test Title 2", "Test Description 2", true)
        )
        
        coEvery { mockRepository.getTodos() } returns flowOf(todoList)
        
        val result: TodoItem? = getTodoByIdUseCase(todoId).first()
        
        assertEquals(todoList[0], result)
    }
    
    @Test
    fun `should return null if todo item not found`() = runTest {
        val todoId = 789
        val todoList = listOf(
            TodoItem(123, "Test Title 1", "Test Description 1", false),
            TodoItem(456, "Test Title 2", "Test Description 2", true)
        )
        coEvery { mockRepository.getTodos() } returns flowOf(todoList)
        
        val result: TodoItem? = getTodoByIdUseCase(todoId).first()
        
        assertEquals(null, result)
    }
    
    @Test
    fun `should return null when repository is empty`() = runTest {
        coEvery { mockRepository.getTodos() } returns flowOf(emptyList())
        
        val result: TodoItem? = getTodoByIdUseCase(123).first()
        
        assertEquals(null, result)
    }
}
