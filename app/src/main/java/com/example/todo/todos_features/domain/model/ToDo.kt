package com.example.todo.todos_features.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    val title: String,
    val content:String,
    val timeStamp: Long,
    @PrimaryKey val id:Int? = null
)
{
    /*companion object {

    }*/
    class InvalidToDoException( message:String):Exception(message)
}