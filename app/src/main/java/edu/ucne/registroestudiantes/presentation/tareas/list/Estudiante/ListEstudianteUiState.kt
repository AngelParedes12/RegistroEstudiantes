package edu.ucne.registroestudiantes.presentation.tareas.list.Estudiante

import edu.ucne.registroestudiantes.domain.model.Estudiante

data class ListEstudianteUIState(
    val estudiantes: List<Estudiante> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)