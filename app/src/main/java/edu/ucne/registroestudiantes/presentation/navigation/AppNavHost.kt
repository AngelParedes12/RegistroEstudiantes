package edu.ucne.registroestudiantes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.registroestudiantes.presentation.tareas.asignaturas.edit.EditAsignaturaScreen
import edu.ucne.registroestudiantes.presentation.tareas.asignaturas.list.ListAsignaturaScreen
import edu.ucne.registroestudiantes.presentation.tareas.edit.Estudiante.EditEstudianteScreen
import edu.ucne.registroestudiantes.presentation.tareas.edit.TipoPenalidad.EditTipoPenalidadScreen
import edu.ucne.registroestudiantes.presentation.tareas.list.Estudiante.ListEstudianteScreen
import edu.ucne.registroestudiantes.presentation.tareas.list.TipoPenalidad.TipoPenalidadListScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListEstudiantes,
        modifier = modifier
    ) {

        composable<Screen.ListEstudiantes> {
            ListEstudianteScreen(
                onAddClick = { navController.navigate(Screen.EditEstudiante()) }
            )
        }

        composable<Screen.EditEstudiante> {
            EditEstudianteScreen(onBack = { navController.popBackStack() })
        }

        composable<Screen.ListAsignaturas> {
            ListAsignaturaScreen(
                onAddClick = { navController.navigate(Screen.EditAsignatura()) }
            )
        }

        composable<Screen.EditAsignatura> {
            EditAsignaturaScreen(onBack = { navController.popBackStack() })
        }

        composable<Screen.ListTipoPenalidad> {
            TipoPenalidadListScreen(
                onAddTipoPenalidad = { navController.navigate(Screen.EditTipoPenalidad()) }
            )
        }

        composable<Screen.EditTipoPenalidad> {
            EditTipoPenalidadScreen(onBack = { navController.popBackStack() })
        }
    }
}