package edu.ucne.registroestudiantes.presentation.tareas.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.registroestudiantes.domain.repository.EstudianteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditEstudianteViewModel @Inject constructor(
    private val repository: EstudianteRepository
) : ViewModel() {
    var uiState by mutableStateOf(EditEstudianteUIState())
        private set
    fun onEvent(event: EditEstudianteUIEvent) {
        when (event) {
            is EditEstudianteUIEvent.OnNombreChange ->
                uiState = uiState.copy(nombres = event.value)

            is EditEstudianteUIEvent.OnEmailChange ->
                uiState = uiState.copy(email = event.value)

            is EditEstudianteUIEvent.OnEdadChange ->
                uiState = uiState.copy(edad = event.value)

            EditEstudianteUIEvent.OnGuardar ->
                guardar()
        }
    }
    private fun guardar() {
        viewModelScope.launch {

            if (
                uiState.nombres.isBlank() ||
                uiState.email.isBlank() ||
                uiState.edad.isBlank()
            ) {
                uiState = uiState.copy(
                    mensajeError = "Todos los campos son obligatorios"
                )
                return@launch
            }

            if (repository.existeNombre(uiState.nombres)) {
                uiState = uiState.copy(
                    mensajeError = "Ya existe un estudiante con ese nombre"
                )
                return@launch
            }

            repository.insertar(
                nombres = uiState.nombres,
                email = uiState.email,
                edad = uiState.edad.toInt()
            )

            uiState = uiState.copy(
                guardadoExitoso = true
            )
        }
    }
    fun limpiarMensaje() {
        uiState = uiState.copy(mensajeError = null)
    }
}