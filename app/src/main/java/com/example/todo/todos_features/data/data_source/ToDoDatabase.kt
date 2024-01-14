package com.example.todo.todos_features.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.todos_features.domain.model.ToDo

@Database(version = 1 , entities = [ToDo::class])
 abstract class ToDoDatabase : RoomDatabase(){
     abstract  val toDoDao: ToDoDao
companion object{
     const val DATABASE_NAME ="ToDo.db"
}
}