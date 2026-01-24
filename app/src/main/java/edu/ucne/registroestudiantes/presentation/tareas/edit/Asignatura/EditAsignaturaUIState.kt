package edu.ucne.registroestudiantes.presentation.tareas.asignaturas.edit

data class EditAsignaturaUIState(
    val codigo: String = "",
    val nombre: String = "",
    val aula: String = "",
    val creditos: String = "",
    val mensajeError: String? = null,
    val guardadoExitoso: Boolean = false
)