package com.albertorusso.mytodo.presentation.todo.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailsScreen(
    navController: NavHostController,
    viewModel: TodoDetailsViewModel = hiltViewModel()
) {
    val todoItem by viewModel.todoItem.collectAsState()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var titleError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    // Sync UI with the fetched TODO
    LaunchedEffect(todoItem) {
        if (todoItem != null) {
            title = todoItem!!.title
            description = todoItem!!.description
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TODO Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        if (todoItem != null) {
            val item = todoItem!!
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Editable Title
                TextField(
                    value = title,
                    onValueChange = {
                        title = it.replaceFirstChar { char -> char.uppercase() }
                        titleError = false // Reset error state
                    },
                    label = { Text("Title") },
                    isError = titleError,
                    modifier = Modifier.fillMaxWidth()
                )
                if (titleError) {
                    Text(
                        text = "Title cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                // Editable Description
                TextField(
                    value = description,
                    onValueChange = {
                        description = it.replaceFirstChar { char -> char.uppercase() }
                        descriptionError = false // Reset error state
                    },
                    label = { Text("Description") },
                    isError = descriptionError,
                    modifier = Modifier.fillMaxWidth()
                )
                if (descriptionError) {
                    Text(
                        text = "Description cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                // Status Display
                Text(
                    text = if (item.isDone) "Status: Done" else "Status: Not Done",
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (item.isDone) Color.Green else Color.Red
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Save Changes Button
                Button(
                    onClick = {
                        if (title.isBlank()) titleError = true
                        if (description.isBlank()) descriptionError = true
                        
                        if (!titleError && !descriptionError) {
                            viewModel.updateTodoItem(title, description)
                            navController.popBackStack() // Navigate back after saving
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Changes")
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Delete Button
                Button(
                    onClick = { showDeleteDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Delete")
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading...", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete TODO") },
            text = { Text("Are you sure you want to delete this TODO?") },
            confirmButton = {
                Button(onClick = {
                    viewModel.deleteTodoItem()
                    showDeleteDialog = false
                    navController.popBackStack() // Navigate back after deletion
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
