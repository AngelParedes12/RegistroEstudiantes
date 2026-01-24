package edu.ucne.registroestudiantes.presentation.navigation

sealed class Routes(val route: String) {
    object ListEstudiantes : Routes("list_estudiantes")
    object EditEstudiante : Routes("edit_estudiante")
    object ListAsignaturas : Routes("list_asignaturas")
    object EditAsignatura : Routes("edit_asignatura")
}