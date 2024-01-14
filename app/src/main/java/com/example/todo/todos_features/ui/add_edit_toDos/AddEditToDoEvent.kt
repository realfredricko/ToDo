package com.example.todo.todos_features.ui.add_edit_toDos

import androidx.compose.ui.focus.FocusState

sealed class AddEditToDoEvent
{
    data class TitleEntry(val value:String):AddEditToDoEvent()
    data class ContentEntry(val value:String):AddEditToDoEvent()
    data class ChangeTitleFocus(val focusState: FocusState):AddEditToDoEvent()
    data class ChangeContentFocus(val focusState: FocusState):AddEditToDoEvent()
    data object SaveToDo:AddEditToDoEvent()
}
