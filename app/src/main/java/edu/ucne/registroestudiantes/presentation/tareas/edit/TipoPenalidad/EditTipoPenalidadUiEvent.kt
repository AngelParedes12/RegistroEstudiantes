package edu.ucne.registroestudiantes.presentation.tareas.edit.TipoPenalidad

sealed interface EditTipoPenalidadUiEvent {
    data class Load(val id: Int?) : EditTipoPenalidadUiEvent

    data class NombreChange(val value: String) : EditTipoPenalidadUiEvent
    data class DescriptionChange(val value: String) : EditTipoPenalidadUiEvent
    data class PuntosDescuentoChange(val value: String) : EditTipoPenalidadUiEvent
    data object Save : EditTipoPenalidadUiEvent
    data object Delete : EditTipoPenalidadUiEvent
}
