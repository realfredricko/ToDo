package com.example.todo.todos_features.domain.use_cases

import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.repository.ToDoRepository

class GetToDosUseCase(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id:Int) : ToDo?
    {
        return repository.getToDosById(id)
    }
}