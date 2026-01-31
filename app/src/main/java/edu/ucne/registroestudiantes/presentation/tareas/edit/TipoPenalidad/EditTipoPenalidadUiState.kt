package edu.ucne.registroestudiantes.presentation.tareas.edit.TipoPenalidad

data class EditTipoPenalidadUiState(
    val penalidadId: Int? = null,
    val nombre: String = "",
    val nombreError: String? = null,
    val descripcion: String = "",
    val puntosDescuento: String = "",
    val descripcionError: String? = null,
    val puntosDescuentoError: String? = null,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val deleted: Boolean = false
)
