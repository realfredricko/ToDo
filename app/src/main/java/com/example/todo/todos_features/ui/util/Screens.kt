package com.example.todo.todos_features.ui.util

sealed class Screens(val route:String){
   data object ToDosScreen:Screens("todos_screen")
    data object AddEditToDoScreen:Screens("add_edit_todo_screen")
}
