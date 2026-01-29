package edu.ucne.registroestudiantes.presentation.navigation

sealed class Screen(val route: String) {
    object ListEstudiantes : Screen("list_estudiantes")
    object EditEstudiante : Screen("edit_estudiante")
    object ListAsignaturas : Screen("list_asignaturas")
    object EditAsignatura : Screen("edit_asignatura")
}