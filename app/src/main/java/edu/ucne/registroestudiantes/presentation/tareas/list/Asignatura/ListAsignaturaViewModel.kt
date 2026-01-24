package edu.ucne.registroestudiantes.presentation.tareas.asignaturas.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.registroestudiantes.domain.repository.AsignaturaRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAsignaturaViewModel @Inject constructor(
    private val repository: AsignaturaRepository
) : ViewModel() {

    var uiState by mutableStateOf(ListAsignaturaUIState())
        private set

    init {
        viewModelScope.launch {
            repository.getAll().collectLatest { list ->
                uiState = uiState.copy(asignaturas = list)
            }
        }
    }

    fun eliminar(asignaturaId: Int) {
        viewModelScope.launch {
            repository.deleteById(asignaturaId)
        }
    }
}