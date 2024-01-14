package com.example.todo.todos_features.ui.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.todos_features.domain.model.ToDo
import com.example.todo.todos_features.domain.use_cases.ToDoUseCases
import com.example.todo.todos_features.domain.utils.OrderType
import com.example.todo.todos_features.domain.utils.ToDoOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDosViewModel @Inject  constructor(
    private val toDoUseCases: ToDoUseCases
):ViewModel(){
private val _state = mutableStateOf<ToDoState>(ToDoState())
    val state: State<ToDoState> =_state

    private var recentlyDeletedToDo : ToDo? = null
    private var    getToDoJob: Job? = null

     init {
       getToDo(ToDoOrder.Date(OrderType.Descending))
     }
    fun onEvent(event: ToDoEvent){
        when(event){
            is ToDoEvent.Order -> {
if (state.value.toDoOrder::class == event.toDoOrder::class &&
    state.value.toDoOrder.orderType == event.toDoOrder.orderType){
    return 
    }
                getToDo(event.toDoOrder)
            }
            is ToDoEvent.DeleteToDo ->{
viewModelScope.launch {
    toDoUseCases.deleteToDos(event.toDo)
    recentlyDeletedToDo = event.toDo

}
            }
            is ToDoEvent.RestoreToDo ->{
viewModelScope.launch {
toDoUseCases.addToDos(recentlyDeletedToDo ?: return@launch)
    recentlyDeletedToDo = null
}
            }
            is ToDoEvent.ToggleOrderSection ->{
                _state.value = state.value.copy(
                    isOrderSectionVisible =!state.value.isOrderSectionVisible
                )

            }
        }
    }

    private fun getToDo(toDoOrder: ToDoOrder) {
        getToDoJob?.cancel()
       getToDoJob = toDoUseCases.getToDos(toDoOrder)
           .onEach { toDo ->
               _state.value=state.value.copy(
                   toDo = toDo,
                   toDoOrder = toDoOrder
               )
           }
           .launchIn(viewModelScope)
    }
}