package com.example.todo.todos_features.ui.add_edit_toDos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todo.todos_features.ui.add_edit_toDos.components.TransparentTextField
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditToDoScreen (
    navController: NavController,
    viewModel: AddEditToDoViewModel = viewModel()
){
    val titleState = viewModel.toDoTitle.value
    val contentState = viewModel.toDoContent.value
    val scaffoldState = rememberScaffoldState()
LaunchedEffect(key1 = true){
    viewModel.eventFlow.collectLatest {
        event ->
        when(event){
            is AddEditToDoViewModel.UIEvent.ShowSnackbar -> {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message
                )
            }
            is AddEditToDoViewModel.UIEvent.SaveToDo -> {
                navController.navigateUp()
            }
        }
    }
}
    Scaffold (floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.onEvent(AddEditToDoEvent.SaveToDo)
        },
            backgroundColor = MaterialTheme.colorScheme.primary) {
            Icon(imageVector = Icons.Default.List, contentDescription = "Save ToDo")
        }
    },
        scaffoldState = scaffoldState){
Column(modifier = Modifier
    .fillMaxSize()
    .padding(16.dp)) {
    TransparentTextField(text = titleState.text,
        hint = titleState.hint,
        onValueChange = {
                        viewModel.onEvent(AddEditToDoEvent.TitleEntry(it))
        } ,
        onFocusChange = {
            viewModel.onEvent(AddEditToDoEvent.ChangeTitleFocus(it))
        },
        isHintVisible = titleState.isHintVisible,
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(16.dp))

    TransparentTextField(text = titleState.text,
        hint = contentState.hint,
        onValueChange = {
            viewModel.onEvent(AddEditToDoEvent.ContentEntry(it))
        } ,
        onFocusChange = {
            viewModel.onEvent(AddEditToDoEvent.ChangeContentFocus(it))
        },
        isHintVisible = contentState.isHintVisible,
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxHeight()
    )
}
    }
}