package edu.ucne.registroestudiantes.presentation.tareas.asignaturas.edit

sealed interface EditAsignaturaUIEvent {
    data class OnCodigoChange(val value: String) : EditAsignaturaUIEvent
    data class OnNombreChange(val value: String) : EditAsignaturaUIEvent
    data class OnAulaChange(val value: String) : EditAsignaturaUIEvent
    data class OnCreditosChange(val value: String) : EditAsignaturaUIEvent
    data object OnGuardar : EditAsignaturaUIEvent
}