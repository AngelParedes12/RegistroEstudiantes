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

    val isEdit = currentRoute == Screen.EditEstudiante.route || currentRoute == Screen.EditAsignatura.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(top = 8.dp)) {

                    NavigationDrawerItem(
                        label = { Text("Estudiantes") },
                        selected = currentRoute == Screen.ListEstudiantes.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Screen.ListEstudiantes.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null) }
                    )

                    NavigationDrawerItem(
                        label = { Text("Asignaturas") },
                        selected = currentRoute == Screen.ListAsignaturas.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Screen.ListAsignaturas.route) {
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
                                Screen.ListAsignaturas.route -> "Asignaturas"
                                Screen.EditAsignatura.route -> "Registrar Asignatura"
                                Screen.EditEstudiante.route -> "Registrar Estudiante"
                                else -> "Estudiantes"
                            }
                        )
                    },
                    navigationIcon = {
                        if (isEdit) {
                            IconButton(
                                onClick = {
                                    when (currentRoute) {
                                        Screen.EditAsignatura.route -> navController.navigate(Screen.ListAsignaturas.route) { launchSingleTop = true }
                                        else -> navController.navigate(Screen.ListEstudiantes.route) { launchSingleTop = true }
                                    }
                                }
                            ) {
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