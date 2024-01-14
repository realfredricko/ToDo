package com.example.todo.todos_features.domain.use_cases

import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.repository.ToDoRepository
import com.example.todo.todos_features.domain.utils.OrderType
import com.example.todo.todos_features.domain.utils.ToDoOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetToDos(private val repository: ToDoRepository) {
    operator fun invoke(toDoOrder: ToDoOrder=ToDoOrder.Date(OrderType.Descending)): Flow<List<ToDo>>{
       return repository.getToDos().map {
           toDo ->
           when(toDoOrder.orderType){
               is OrderType.Ascending -> {
                   when(toDoOrder){
                       is ToDoOrder.Title ->toDo.sortedBy { it.title.lowercase() }
                         is ToDoOrder.Date ->toDo.sortedBy { it.timeStamp }
                       }
                   }
               is OrderType.Descending -> {
                   when(toDoOrder){
                       is ToDoOrder.Title ->toDo.sortedByDescending { it.title.lowercase() }
                       is ToDoOrder.Date -> toDo.sortedByDescending {it.timeStamp  }
                   }
               }
               }

               }
           }
       }