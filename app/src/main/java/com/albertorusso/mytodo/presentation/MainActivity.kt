package com.albertorusso.mytodo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.albertorusso.mytodo.domain.model.TodoItem
import com.albertorusso.mytodo.presentation.navigation.TodoApp
import com.albertorusso.mytodo.presentation.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

val todoItems = listOf(
    TodoItem(1, "Give an initial call", "For the candidate Alberto Russo, I should make an initial call to get to know him and see if he is a good " +
            "fit for this role", true),
    TodoItem(2, "Send assignment", "For the candidate Alberto Russo, I should send him a technical assignment to see if he is able to deliver what " +
            "we require", false),
    TodoItem(3, "Make hiring decision", "For the candidate Alberto Russo, I should decide if I should hire him ", false)
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                TodoApp()
            }
        }
    }
}
