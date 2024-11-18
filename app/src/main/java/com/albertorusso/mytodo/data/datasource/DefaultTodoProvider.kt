package com.albertorusso.mytodo.data.datasource

import com.albertorusso.mytodo.data.model.TodoEntity

object DefaultTodoProvider {
    fun getDefaultTodos(): List<TodoEntity> {
        return listOf(
            TodoEntity(1, "Give an initial call", "For the candidate Alberto Russo, I should make an initial call to get to know him and see if he is a good fit for this role", true),
            TodoEntity(2, "Send assignment", "For the candidate Alberto Russo, I should send him a technical assignment to see if he is able to deliver what we require", false),
            TodoEntity(3, "Make hiring decision", "For the candidate Alberto Russo, I should decide if I should hire him", false)
        )
    }
}
