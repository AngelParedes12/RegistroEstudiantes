package edu.ucne.registroestudiantes.presentation.tareas.list.Estudiante

sealed interface ListEstudianteUIEvent {
    data object OnLoad : ListEstudianteUIEvent
}