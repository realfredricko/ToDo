package com.example.todo.todos_features.data.repository

import com.example.todo.todos_features.data.data_source.ToDoDao
import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(private val dao: ToDoDao):ToDoRepository {
    override fun getToDos(): Flow<List<ToDo>> {
       return dao.getToDos()
    }

    override suspend fun getToDosById(id: Int): ToDo? {
       return dao.getToDoById(id)
    }

    override suspend fun insertToDo(toDo: ToDo) {
        dao.insertToDo(toDo)
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        dao.deleteToDo(toDo)
    }
}