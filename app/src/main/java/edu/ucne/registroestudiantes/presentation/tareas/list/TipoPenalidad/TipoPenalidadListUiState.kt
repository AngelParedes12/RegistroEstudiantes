package edu.ucne.registroestudiantes.presentation.tareas.list.TipoPenalidad

import edu.ucne.registroestudiantes.domain.model.TipoPenalidad

data class TipoPenalidadListUiState(
    val isLoading: Boolean = false,
    val tipoPenalidades: List<TipoPenalidad> = emptyList(),
    val message: String? = null,
    val navigateToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)
