package edu.ucne.registroestudiantes.domain.usecase.penalidades

import edu.ucne.registroestudiantes.domain.model.TipoPenalidad
import edu.ucne.registroestudiantes.domain.repository.TipoPenalidadRepository
import javax.inject.Inject

class GetPenalidadUseCase @Inject constructor(
    private val repository: TipoPenalidadRepository
) {
    suspend operator fun invoke(id: Int): TipoPenalidad? = repository.getTipoPenalidad(id)
}
