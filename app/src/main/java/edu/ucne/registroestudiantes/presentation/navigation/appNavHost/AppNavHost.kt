package edu.ucne.registroestudiantes.presentation.navigation.appNavHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.registroestudiantes.presentation.navigation.routes.Routes
import edu.ucne.registroestudiantes.presentation.tareas.edit.EditEstudianteScreen
import edu.ucne.registroestudiantes.presentation.tareas.list.ListEstudianteScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.List.route,
        modifier = modifier
    ) {
        composable(Routes.List.route) {
            ListEstudianteScreen(
                onAddClick = {
                    navController.navigate(Routes.Edit.route)
                }
            )
        }

        composable(Routes.Edit.route) {
            EditEstudianteScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}