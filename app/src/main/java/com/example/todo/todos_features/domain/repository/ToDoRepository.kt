package com.example.todo.todos_features.domain.repository

import com.example.todo.todos_features.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getToDos(): Flow<List<ToDo>>
    suspend fun getToDosById(id:Int):ToDo?

    suspend fun insertToDo(toDo: ToDo)

    suspend fun deleteToDo(toDo: ToDo)
}