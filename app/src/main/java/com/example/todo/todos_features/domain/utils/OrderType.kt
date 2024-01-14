package com.example.todo.todos_features.domain.utils

sealed class OrderType{
    data object Ascending:OrderType()
    data object Descending:OrderType()
}
