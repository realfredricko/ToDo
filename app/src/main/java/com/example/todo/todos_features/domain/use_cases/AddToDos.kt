package com.example.todo.todos_features.domain.use_cases

import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.repository.ToDoRepository
import kotlin.jvm.Throws

class AddToDos (private val repository: ToDoRepository) {
    @Throws(ToDo.InvalidToDoException::class)
    suspend  operator fun invoke(toDo :ToDo){
        if(toDo.title.isBlank()){
            throw ToDo.InvalidToDoException("The title of todo cannot be blank!")
        }
        if (toDo.content.isBlank()){
            throw ToDo.InvalidToDoException("The content of todo cannot be blank!")
        }
        repository.insertToDo(toDo)
    }
}