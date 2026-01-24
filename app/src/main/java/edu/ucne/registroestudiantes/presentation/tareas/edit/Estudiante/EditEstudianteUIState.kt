package edu.ucne.registroestudiantes.presentation.tareas.edit.Estudiante

data class EditEstudianteUIState(
    val nombres: String = "",
    val email: String = "",
    val edad: String = "",
    val mensajeError: String? = null,
    val guardadoExitoso: Boolean = false
)