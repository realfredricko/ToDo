package com.example.todo.todos_features.ui.todos

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.todos_features.ui.todos.components.OrderSection
import com.example.todo.todos_features.ui.todos.components.ToDoItem
import com.example.todo.todos_features.ui.util.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun ToDosScreen(
    navController: NavController,
    vm:ToDosViewModel = viewModel()
) {
    val state = vm.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
                                       navController.navigate("add_edit_todo_screen")
        },
            backgroundColor = MaterialTheme.colors.primary ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add ToDo")
        }
    },
        scaffoldState = scaffoldState) {
Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
) {
    Row(
modifier = Modifier
    .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ){
Text(text = "ToDos",
    style = MaterialTheme.typography.h4
)
        IconButton(onClick = {
            vm.onEvent(ToDoEvent.ToggleOrderSection)
        }) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Sort ToDos"
            )
        }
    }
     AnimatedVisibility(visible = state.isOrderSectionVisible,
         enter = expandVertically(),
         exit = shrinkVertically()
     ) {
         OrderSection(modifier= Modifier
             .fillMaxWidth()
             .padding(vertical = 16.dp),
             toDoOrder = state.toDoOrder,
             onOrderChange = {
                 vm.onEvent(ToDoEvent.Order(it))
             })
     }
    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(modifier = Modifier
        .fillMaxSize() ){
        items(state.toDo){ todo ->
            ToDoItem(toDo = todo,
                modifier =Modifier
                    .fillMaxWidth()
                    .clickable {
navController.navigate(Screens.AddEditToDoScreen.route +"?toDoId= ${todo.id}")
                    },
                onDeleteClick = {
                    vm.onEvent(event = ToDoEvent.DeleteToDo(todo))
                    scope.launch {
                        val output = scaffoldState.snackbarHostState.showSnackbar(
                            message = "ToDo Deleted",
                            actionLabel = "UnDo Delete"
                        )
                        if ( output == SnackbarResult.ActionPerformed){
                            vm.onEvent(ToDoEvent.RestoreToDo)
                        }
                    }
                })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
  }
}
}
