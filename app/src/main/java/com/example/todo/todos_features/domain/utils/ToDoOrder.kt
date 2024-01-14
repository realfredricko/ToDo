package com.example.todo.todos_features.domain.utils

sealed class ToDoOrder(val orderType: OrderType){
     class Title(orderType: OrderType):ToDoOrder(orderType)
    class Date(orderType: OrderType):ToDoOrder(orderType)

    fun copy(orderType: OrderType) : ToDoOrder{

        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}
