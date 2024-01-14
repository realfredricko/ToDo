package com.example.todo.todos_features.domain.use_cases

import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.repository.ToDoRepository

class DeleteToDos( private val repository: ToDoRepository) {
    suspend operator fun invoke(toDo: ToDo){
        repository.deleteToDo(toDo)
    }
}