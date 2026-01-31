package edu.ucne.registroestudiantes.domain.usecase.penalidades

import edu.ucne.registroestudiantes.domain.repository.TipoPenalidadRepository
import javax.inject.Inject

class DeletePenalidadUseCase @Inject constructor(
    private val repository: TipoPenalidadRepository
) {
    suspend operator fun invoke(id: Int) = repository.delete(id)
}
