package edu.ucne.registroestudiantes.presentation.tareas.list.TipoPenalidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.registroestudiantes.domain.usecase.penalidades.DeletePenalidadUseCase
import edu.ucne.registroestudiantes.domain.usecase.penalidades.ObservePenalidadesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipoPenalidadListViewModel @Inject constructor(
    private val observePenalidadesUseCase: ObservePenalidadesUseCase,
    private val deletePenalidadUseCase: DeletePenalidadUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TipoPenalidadListUiState(isLoading = true))
    val state: StateFlow<TipoPenalidadListUiState> = _state.asStateFlow()

    init {
        loadTipoPenalidad()
    }

    fun onEvent(event: TipoPenalidadListUiEvent) {
        when (event) {
            TipoPenalidadListUiEvent.Load -> loadTipoPenalidad()
            TipoPenalidadListUiEvent.Refresh -> loadTipoPenalidad()
            is TipoPenalidadListUiEvent.Delete -> onDelete(event.id)
            is TipoPenalidadListUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            TipoPenalidadListUiEvent.ClearMessage -> _state.update { it.copy(message = null) }
            TipoPenalidadListUiEvent.CreateNew -> _state.update { it.copy(navigateToCreate = true) }
            is TipoPenalidadListUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
        }
    }

    private fun loadTipoPenalidad() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observePenalidadesUseCase().collectLatest { list ->
                _state.update { it.copy(isLoading = false, tipoPenalidades = list) }
            }
        }
    }

    private fun onDelete(id: Int) {
        viewModelScope.launch {
            deletePenalidadUseCase(id)
            onEvent(TipoPenalidadListUiEvent.ShowMessage("Tipo Penalidad Eliminado"))
        }
    }
}
