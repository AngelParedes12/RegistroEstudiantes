package edu.ucne.registroestudiantes.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.ucne.registroestudiantes.presentation.navigation.appNavHost.AppNavHost
import edu.ucne.registroestudiantes.presentation.navigation.routes.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerShell(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val isEdit = currentRoute == Routes.EditEstudiante.route || currentRoute == Routes.EditAsignatura.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    NavigationDrawerItem(
                        label = { Text("Estudiantes") },
                        selected = currentRoute == Routes.ListEstudiantes.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Routes.ListEstudiantes.route) {
                                popUpTo(Routes.ListEstudiantes.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null) }
                    )

                    NavigationDrawerItem(
                        label = { Text("Asignaturas") },
                        selected = currentRoute == Routes.ListAsignaturas.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Routes.ListAsignaturas.route) {
                                popUpTo(Routes.ListEstudiantes.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.Filled.Book, contentDescription = null) }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            when (currentRoute) {
                                Routes.ListAsignaturas.route -> "Asignaturas"
                                Routes.EditAsignatura.route -> "Registrar Asignatura"
                                Routes.EditEstudiante.route -> "Registrar Estudiante"
                                else -> "Estudiantes"
                            }
                        )
                    },
                    navigationIcon = {
                        if (isEdit) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                            }
                        } else {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = null)
                            }
                        }
                    }
                )
            }
        ) { padding ->
            AppNavHost(
                navController = navController,
                modifier = modifier.padding(padding)
            )
        }
    }
}