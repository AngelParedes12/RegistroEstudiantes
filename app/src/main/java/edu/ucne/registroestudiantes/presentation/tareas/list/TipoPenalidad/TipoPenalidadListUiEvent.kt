package edu.ucne.registroestudiantes.presentation.tareas.list.TipoPenalidad

sealed class TipoPenalidadListUiEvent {

        object Load : TipoPenalidadListUiEvent()
        object Refresh : TipoPenalidadListUiEvent()
        data class Delete(val id: Int) : TipoPenalidadListUiEvent()
        data class ShowMessage(val message: String) : TipoPenalidadListUiEvent()
        object ClearMessage : TipoPenalidadListUiEvent()
        object CreateNew : TipoPenalidadListUiEvent()
        data class Edit(val id: Int) : TipoPenalidadListUiEvent()


}

