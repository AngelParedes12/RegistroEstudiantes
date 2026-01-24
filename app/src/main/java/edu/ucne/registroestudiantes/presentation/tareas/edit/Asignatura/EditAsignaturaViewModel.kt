package edu.ucne.registroestudiantes.presentation.tareas.asignaturas.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.registroestudiantes.data.local.entities.AsignaturaEntity
import edu.ucne.registroestudiantes.domain.repository.AsignaturaRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAsignaturaViewModel @Inject constructor(
    private val repository: AsignaturaRepository
) : ViewModel() {

    var uiState by mutableStateOf(EditAsignaturaUIState())
        private set

    fun onEvent(event: EditAsignaturaUIEvent) {
        when (event) {
            is EditAsignaturaUIEvent.OnCodigoChange -> uiState = uiState.copy(codigo = event.value)
            is EditAsignaturaUIEvent.OnNombreChange -> uiState = uiState.copy(nombre = event.value)
            is EditAsignaturaUIEvent.OnAulaChange -> uiState = uiState.copy(aula = event.value)
            is EditAsignaturaUIEvent.OnCreditosChange -> uiState = uiState.copy(creditos = event.value)
            EditAsignaturaUIEvent.OnGuardar -> guardar()
        }
    }

    private fun guardar() {
        viewModelScope.launch {
            val codigo = uiState.codigo.trim()
            val nombre = uiState.nombre.trim()
            val aula = uiState.aula.trim()
            val creditosInt = uiState.creditos.trim().toIntOrNull()

            if (codigo.isBlank() || nombre.isBlank() || aula.isBlank() || creditosInt == null || creditosInt <= 0) {
                uiState = uiState.copy(mensajeError = "Todos los campos son obligatorios")
                return@launch
            }

            if (repository.existsNombre(nombre)) {
                uiState = uiState.copy(mensajeError = "Ya existe una asignatura registrada con este nombre")
                return@launch
            }

            repository.insert(
                AsignaturaEntity(
                    codigo = codigo,
                    nombre = nombre,
                    aula = aula,
                    creditos = creditosInt
                )
            )

            uiState = uiState.copy(guardadoExitoso = true)
        }
    }

    fun limpiarMensaje() {
        uiState = uiState.copy(mensajeError = null)
    }
}