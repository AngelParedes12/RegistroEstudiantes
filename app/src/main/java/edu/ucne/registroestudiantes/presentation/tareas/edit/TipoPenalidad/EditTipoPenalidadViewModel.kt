package edu.ucne.registroestudiantes.presentation.tareas.edit.TipoPenalidad

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.registroestudiantes.domain.model.TipoPenalidad
import edu.ucne.registroestudiantes.domain.usaCase.TipoPenalidad.UpsertTipoPenalidadUseCase
import edu.ucne.registroestudiantes.domain.usecase.penalidades.DeletePenalidadUseCase
import edu.ucne.registroestudiantes.domain.usecase.penalidades.GetPenalidadUseCase
import edu.ucne.registroestudiantes.domain.validation.validateDescripcion
import edu.ucne.registroestudiantes.domain.validation.validateNombre
import edu.ucne.registroestudiantes.domain.validation.validatePuntosDescuento
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTipoPenalidadViewModel @Inject constructor(
    private val getPenalidadUseCase: GetPenalidadUseCase,
    private val upsertTipoPenalidadUseCase: UpsertTipoPenalidadUseCase,
    private val deletePenalidadUseCase: DeletePenalidadUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val penalidadId: Int = savedStateHandle["PenalidadId"] ?: 0

    private val _state = MutableStateFlow(EditTipoPenalidadUiState())
    val state: StateFlow<EditTipoPenalidadUiState> = _state.asStateFlow()

    init {
        loadTipoPenalidad(penalidadId)
    }

    fun onEvent(event: EditTipoPenalidadUiEvent) {
        when (event) {
            is EditTipoPenalidadUiEvent.Load -> loadTipoPenalidad(event.id)

            is EditTipoPenalidadUiEvent.NombreChange -> _state.update {
                it.copy(nombre = event.value, nombreError = null)
            }

            is EditTipoPenalidadUiEvent.DescriptionChange -> _state.update {
                it.copy(descripcion = event.value, descripcionError = null)
            }

            is EditTipoPenalidadUiEvent.PuntosDescuentoChange -> _state.update {
                it.copy(puntosDescuento = event.value, puntosDescuentoError = null)
            }

            EditTipoPenalidadUiEvent.Save -> onSave()
            EditTipoPenalidadUiEvent.Delete -> onDelete()
        }
    }

    private fun loadTipoPenalidad(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, penalidadId = null) }
            return
        }

        viewModelScope.launch {
            val tipoPenalidad = getPenalidadUseCase(id)
            if (tipoPenalidad != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        penalidadId = tipoPenalidad.penalidadId,
                        nombre = tipoPenalidad.nombre,
                        descripcion = tipoPenalidad.descripcion,
                        puntosDescuento = tipoPenalidad.puntosDescuento.toString()
                    )
                }
            } else {
                _state.update { it.copy(isNew = true, penalidadId = null) }
            }
        }
    }

    private fun onSave() {
        val nombreResult = validateNombre(state.value.nombre)
        val descResult = validateDescripcion(state.value.descripcion)
        val puntosResult = validatePuntosDescuento(state.value.puntosDescuento)

        if (!nombreResult.isValid || !descResult.isValid || !puntosResult.isValid) {
            _state.update {
                it.copy(
                    nombreError = nombreResult.error,
                    descripcionError = descResult.error,
                    puntosDescuentoError = puntosResult.error
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }

            val tipoPenalidad = TipoPenalidad(
                penalidadId = state.value.penalidadId ?: 0,
                nombre = state.value.nombre,
                descripcion = state.value.descripcion,
                puntosDescuento = state.value.puntosDescuento.toInt()
            )

            val result = upsertTipoPenalidadUseCase(tipoPenalidad)

            result.onSuccess { newId ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        saved = true,
                        penalidadId = newId,
                        isNew = false
                    )
                }
            }.onFailure { ex ->
                _state.update {
                    val msg = ex.message ?: "Error al guardar"
                    it.copy(
                        isSaving = false,
                        nombreError = if (msg.contains("Ya existe", ignoreCase = true)) msg else it.nombreError
                    )
                }
            }
        }
    }

    private fun onDelete() {
        val id = state.value.penalidadId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deletePenalidadUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}