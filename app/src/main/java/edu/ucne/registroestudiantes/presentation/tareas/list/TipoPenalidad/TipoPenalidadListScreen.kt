package edu.ucne.registroestudiantes.presentation.tareas.list.TipoPenalidad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.registroestudiantes.domain.model.TipoPenalidad

@Composable
fun TipoPenalidadListScreen(
    viewModel: TipoPenalidadListViewModel = hiltViewModel(),
    onAddTipoPenalidad: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TipoPenalidadListBody(state, viewModel::onEvent, onAddTipoPenalidad)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipoPenalidadListBody(
    state: TipoPenalidadListUiState,
    onEvent: (TipoPenalidadListUiEvent) -> Unit,
    onAddTipoPenalidad: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(TipoPenalidadListUiEvent.ClearMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTipoPenalidad,
                modifier = Modifier.testTag("fab_add")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar penalidad")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center).testTag("loading")
                )
            } else {
                if (state.tipoPenalidades.isEmpty()) {
                    Text(
                        text = "No hay penalidades",
                        modifier = Modifier.align(Alignment.Center).testTag("empty_message")
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.tipoPenalidades,
                            key = { it.penalidadId }
                        ) { tipoPenalidad ->
                            TipoPenalidadItem(
                                tipoPenalidad = tipoPenalidad,
                                onDelete = {
                                    onEvent(TipoPenalidadListUiEvent.Delete(tipoPenalidad.penalidadId))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TipoPenalidadItem(
    tipoPenalidad: TipoPenalidad,
    onDelete: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("penalidad_item_${tipoPenalidad.penalidadId}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(tipoPenalidad.nombre, style = MaterialTheme.typography.bodyLarge)
                Text(tipoPenalidad.descripcion, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "${tipoPenalidad.puntosDescuento} pts",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(
                onClick = onDelete,
                modifier = Modifier.testTag("btn_delete_${tipoPenalidad.penalidadId}")
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar penalidad")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TipoPenalidadBodyPreview() {
    MaterialTheme {
        val state = TipoPenalidadListUiState(
            isLoading = false,
            tipoPenalidades = listOf(
                TipoPenalidad(
                    penalidadId = 1,
                    nombre = "Copiando",
                    descripcion = "Copiando en el examen",
                    puntosDescuento = 30
                ),
                TipoPenalidad(
                    penalidadId = 2,
                    nombre = "Se paró",
                    descripcion = "Se paró el examen",
                    puntosDescuento = 10
                )
            )
        )
        TipoPenalidadListBody(state, {}, {})
    }
}