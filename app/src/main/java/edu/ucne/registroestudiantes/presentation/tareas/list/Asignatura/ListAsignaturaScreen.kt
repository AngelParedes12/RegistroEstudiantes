package edu.ucne.registroestudiantes.presentation.tareas.asignaturas.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.registroestudiantes.data.local.entities.AsignaturaEntity

@Composable
fun ListAsignaturaScreen(
    onAddClick: () -> Unit,
    viewModel: ListAsignaturaViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) { Text("+") }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.asignaturas.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 90.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text("No hay asignaturas registradas")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                        .padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.asignaturas) { asignatura ->
                        AsignaturaItem(
                            asignatura = asignatura,
                            onEliminar = { viewModel.eliminar(it.asignaturaId) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AsignaturaItem(
    asignatura: AsignaturaEntity,
    onEliminar: (AsignaturaEntity) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = asignatura.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "Código: ${asignatura.codigo}")
                Text(text = "Aula: ${asignatura.aula}")
                Text(text = "Créditos: ${asignatura.creditos}")
            }

            IconButton(onClick = { onEliminar(asignatura) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}