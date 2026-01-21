package edu.ucne.registroestudiantes.presentation.tareas.edit

sealed interface EditEstudianteUIEvent {
    data class OnNombreChange(val value: String) : EditEstudianteUIEvent
    data class OnEmailChange(val value: String) : EditEstudianteUIEvent
    data class OnEdadChange(val value: String) : EditEstudianteUIEvent
    data object OnGuardar : EditEstudianteUIEvent
}