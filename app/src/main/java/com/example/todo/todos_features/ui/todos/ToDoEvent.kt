package com.example.todo.todos_features.ui.todos

import androidx.compose.foundation.layout.PaddingValues
import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.utils.ToDoOrder

sealed class ToDoEvent{
    data class Order(val toDoOrder: ToDoOrder):ToDoEvent()
    data class DeleteToDo(val toDo:ToDo):ToDoEvent()
    data object RestoreToDo: ToDoEvent()
    data object ToggleOrderSection:ToDoEvent()
}
