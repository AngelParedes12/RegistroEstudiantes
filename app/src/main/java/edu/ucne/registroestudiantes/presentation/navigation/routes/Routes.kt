package edu.ucne.registroestudiantes.presentation.navigation.routes

sealed class Routes(val route: String) {
    object List : Routes("list_estudiantes")
    object Edit : Routes("edit_estudiante")
}