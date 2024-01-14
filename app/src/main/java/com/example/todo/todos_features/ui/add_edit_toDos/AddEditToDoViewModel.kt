package com.example.todo.todos_features.ui.add_edit_toDos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.use_cases.ToDoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditToDoViewModel @Inject constructor(
    private val toDoUseCases: ToDoUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    init{
        savedStateHandle.get<Int>("toDoId")?.let{ toDoId ->
            if (toDoId != -1){
                viewModelScope.launch {
                    toDoUseCases.getToDosUseCase(toDoId)?.also { toDo ->
                        currentToDoId = toDo.id
                        _toDoTitle.value = toDoTitle.value.copy(
                            text = toDo.title,
                            isHintVisible = false
                        )
                        _toDoContent.value = toDoContent.value.copy(
                            text = toDo.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }
    private val _toDoTitle = mutableStateOf(ToDoTextFieldState(
        hint = "Enter ToDo's Title ..."
    ))
     val toDoTitle: State<ToDoTextFieldState> = _toDoTitle

        private val _toDoContent = mutableStateOf(ToDoTextFieldState(
            hint = "Enter some ToDo's content..."
        ))
     val toDoContent:State<ToDoTextFieldState> = _toDoContent

    private val _eventFlow =  MutableSharedFlow<UIEvent>()
val eventFlow:Flow<UIEvent> = _eventFlow.asSharedFlow()

    private var currentToDoId: Int? = null

   sealed class UIEvent {
       data class ShowSnackbar(val message: String) : UIEvent()
       data object SaveToDo : UIEvent()
   }
    fun onEvent(
        event: AddEditToDoEvent
    ){
        when(event){
            is  AddEditToDoEvent.TitleEntry -> {
_toDoTitle.value = toDoTitle.value.copy(
    text = event.value
)
            }
            is AddEditToDoEvent.ChangeTitleFocus -> {
_toDoTitle.value = toDoTitle.value.copy(
    isHintVisible = !event.focusState.isFocused && toDoTitle.value.text.isBlank()
)
            }
            is AddEditToDoEvent.ContentEntry -> {
                _toDoContent.value = toDoContent.value.copy(
                    text = event.value)
            }
            is AddEditToDoEvent.ChangeContentFocus -> {
                _toDoContent.value = toDoContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && toDoContent.value.text.isBlank())
            }
            is AddEditToDoEvent.SaveToDo -> {
viewModelScope.launch {
    try {
       toDoUseCases.addToDos(
           ToDo(
               title = toDoTitle.value.text,
               content = toDoContent.value.text,
               timeStamp = System.currentTimeMillis(),
               id = currentToDoId
           )
       )
        _eventFlow.emit(UIEvent.SaveToDo)
    }
    catch (e:ToDo.InvalidToDoException){
        _eventFlow.emit(
            UIEvent.ShowSnackbar(
                message = e.message?:  "Couldn't Save ToDo!"
            )
        )
    }
}
            }
        }
    }
    }



