package com.albertorusso.mytodo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albertorusso.mytodo.presentation.todo.create.TodoCreateScreen
import com.albertorusso.mytodo.presentation.todo.details.TodoDetailsScreen
import com.albertorusso.mytodo.presentation.todo.list.TodoListScreen

@Composable
fun TodoApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") {
            TodoListScreen(navController)
        }
        composable("todo_create") {
            TodoCreateScreen(navController = navController)
        }
        composable("todo_details/{todoId}") { _ ->
            TodoDetailsScreen(navController = navController)
        }
    }
}
