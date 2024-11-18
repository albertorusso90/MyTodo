package com.albertorusso.mytodo.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.albertorusso.mytodo.data.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): Flow<List<TodoEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<TodoEntity>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)
    
    @Query("UPDATE todo_table SET isDone = :isDone WHERE id = :id")
    suspend fun updateTodoStatus(id: Int, isDone: Boolean)
    
    @Query("DELETE FROM todo_table WHERE id = :id")
    suspend fun deleteTodoById(id: Int)
}
