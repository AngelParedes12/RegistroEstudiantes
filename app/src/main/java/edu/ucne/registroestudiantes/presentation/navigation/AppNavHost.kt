package edu.ucne.registroestudiantes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.registroestudiantes.presentation.tareas.edit.Estudiante.EditEstudianteScreen
import edu.ucne.registroestudiantes.presentation.tareas.list.Estudiante.ListEstudianteScreen
import edu.ucne.registroestudiantes.presentation.tareas.asignaturas.edit.EditAsignaturaScreen
import edu.ucne.registroestudiantes.presentation.tareas.asignaturas.list.ListAsignaturaScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListEstudiantes.route,
        modifier = modifier
    ) {
        composable(Screen.ListEstudiantes.route) {
            ListEstudianteScreen(
                onAddClick = { navController.navigate(Screen.EditEstudiante.route) }
            )
        }

        composable(Screen.EditEstudiante.route) {
            EditEstudianteScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.ListAsignaturas.route) {
            ListAsignaturaScreen(
                onAddClick = { navController.navigate(Screen.EditAsignatura.route) }
            )
        }

        composable(Screen.EditAsignatura.route) {
            EditAsignaturaScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}