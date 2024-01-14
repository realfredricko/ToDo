package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.todos_features.data.data_source.ToDoDatabase
import com.example.todo.todos_features.data.repository.ToDoRepositoryImpl
import com.example.todo.todos_features.domain.repository.ToDoRepository
import com.example.todo.todos_features.domain.use_cases.AddToDos
import com.example.todo.todos_features.domain.use_cases.DeleteToDos
import com.example.todo.todos_features.domain.use_cases.GetToDos
import com.example.todo.todos_features.domain.use_cases.GetToDosUseCase
import com.example.todo.todos_features.domain.use_cases.ToDoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object AppModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {
        @Provides
        @Singleton
        fun provideToDoDatabase(app: Application): ToDoDatabase {
            return Room.databaseBuilder(
                app,
                ToDoDatabase::class.java,
                ToDoDatabase.DATABASE_NAME
            ).build()
        }
        @Provides
        @Singleton
        fun provideToDosRepository(db:ToDoDatabase):ToDoRepository{
            return ToDoRepositoryImpl(db.toDoDao)
        }
        @Provides
        @Singleton
        fun provideToDoUseCases(repository:ToDoRepository): ToDoUseCases{
            return ToDoUseCases(
                getToDos = GetToDos(repository),
                deleteToDos = DeleteToDos(repository),
                addToDos = AddToDos(repository),
                getToDosUseCase = GetToDosUseCase(repository)
            )
        }
    }
}