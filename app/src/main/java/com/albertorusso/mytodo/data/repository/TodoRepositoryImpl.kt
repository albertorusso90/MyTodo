package com.albertorusso.mytodo.data.repository

import com.albertorusso.mytodo.data.datasource.TodoDao
import com.albertorusso.mytodo.data.model.TodoEntity
import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(private val todoDao: TodoDao) : TodoRepository {
    
    override fun getTodos(): Flow<List<TodoItem>> {
        return todoDao.getAllTodos().map { entities ->
            entities.map { entity ->
                TodoItem(entity.id, entity.title, entity.description, entity.isDone)
            }
        }
    }
    
    override suspend fun insertTodo(todo: TodoItem) {
        todoDao.insertTodo(TodoEntity(todo.id, todo.title, todo.description, todo.isDone))
    }
    
    override suspend fun updateTodo(todo: TodoItem) {
        val entity = TodoEntity(todo.id, todo.title, todo.description, todo.isDone)
        todoDao.insertTodo(entity)
    }
    
    override suspend fun updateTodoStatus(id: Int, isDone: Boolean) {
        todoDao.updateTodoStatus(id, isDone)
    }
    
    override suspend fun deleteTodo(todoId: Int) {
        todoDao.deleteTodoById(todoId)
    }
}
