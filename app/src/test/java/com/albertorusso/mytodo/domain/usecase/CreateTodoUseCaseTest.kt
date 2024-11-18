package com.albertorusso.mytodo.domain.usecase

import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.domain.repository.TodoRepository
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class CreateTodoUseCaseTest {
    
    private lateinit var createTodoUseCase: CreateTodoUseCase
    private val mockRepository: TodoRepository = mockk(relaxed = true)
    
    @Before
    fun setUp() {
        createTodoUseCase = CreateTodoUseCase(mockRepository)
    }
    
    @Test
    fun `should create a new todo item with unique id`() = runTest {
        val title = "Test Title"
        val description = "Test Description"
        val slot = slot<TodoItem>()
        
        createTodoUseCase(title, description)
        
        coVerify { mockRepository.insertTodo(capture(slot)) }
        
        val capturedTodo = slot.captured
        assertEquals(title, capturedTodo.title)
        assertEquals(description, capturedTodo.description)
        assertEquals(false, capturedTodo.isDone)
        assert(capturedTodo.id > 0)
    }
}
