package com.example.todo.todos_features.ui.todos

import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.utils.OrderType
import com.example.todo.todos_features.domain.utils.ToDoOrder

data class ToDoState(
    val toDo : List<ToDo> = emptyList(),
    val toDoOrder: ToDoOrder =ToDoOrder.Date(OrderType.Descending),
    var isOrderSectionVisible:Boolean = false
    )
