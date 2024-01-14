package com.example.todo.todos_features.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.todos_features.ui.add_edit_toDos.AddEditToDoScreen
import com.example.todo.todos_features.ui.todos.ToDosScreen
import com.example.todo.todos_features.ui.util.Screens
import com.example.todo.ui.theme.ToDoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface (
                    color = MaterialTheme.colors.background
                ){
                    val navController = rememberNavController()
                    NavHost(navController = navController ,
                        startDestination = Screens.ToDosScreen.route,
                        ) {
                        composable(route = Screens.ToDosScreen.route){
                            ToDosScreen(navController = navController)
                    }
                        composable(route = Screens.AddEditToDoScreen.route + "?toDoId = {toDoId}",
                            arguments = listOf(
                                navArgument(
                                    "toDoId"
                                ){
                                    type = NavType.IntType
                                    defaultValue =-1
                                }
                            )){
AddEditToDoScreen(navController = navController)
                        }
                    }
                }

            }
        }
    }
}


