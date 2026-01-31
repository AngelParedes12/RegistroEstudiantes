package edu.ucne.registroestudiantes.domain.usecase.penalidades

import edu.ucne.registroestudiantes.domain.model.TipoPenalidad
import edu.ucne.registroestudiantes.domain.repository.TipoPenalidadRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePenalidadesUseCase @Inject constructor(
    private val repository: TipoPenalidadRepository
) {
    operator fun invoke(): Flow<List<TipoPenalidad>> = repository.observePenalidades()
}
