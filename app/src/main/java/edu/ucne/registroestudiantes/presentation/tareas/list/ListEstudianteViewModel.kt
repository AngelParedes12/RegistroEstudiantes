package edu.ucne.registroestudiantes.presentation.tareas.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.registroestudiantes.domain.model.Estudiante
import edu.ucne.registroestudiantes.domain.repository.EstudianteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListEstudianteViewModel @Inject constructor(
    private val repository: EstudianteRepository
) : ViewModel() {
    var uiState by mutableStateOf(ListEstudianteUIState())
        private set

    init {
        cargar()
    }
    fun cargar() {
        viewModelScope.launch {
            uiState = uiState.copy(
                estudiantes = repository.obtenerTodos()
            )
        }
    }
    fun eliminar(estudiante: Estudiante) {
        viewModelScope.launch {
            repository.eliminar(estudiante)
            cargar()
        }
    }
}