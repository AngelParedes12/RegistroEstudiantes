package edu.ucne.registroestudiantes.domain.usaCase.TipoPenalidad

import edu.ucne.registroestudiantes.domain.model.TipoPenalidad
import edu.ucne.registroestudiantes.domain.repository.TipoPenalidadRepository
import edu.ucne.registroestudiantes.domain.validation.validateDescripcion
import edu.ucne.registroestudiantes.domain.validation.validateNombre
import edu.ucne.registroestudiantes.domain.validation.validatePuntosDescuento
import javax.inject.Inject

class UpsertTipoPenalidadUseCase @Inject constructor(
    private val repository: TipoPenalidadRepository
) {
    suspend operator fun invoke(tipoPenalidad: TipoPenalidad): Result<Int> {

        val nombreResult = validateNombre(tipoPenalidad.nombre)
        if (!nombreResult.isValid) {
            return Result.failure(IllegalArgumentException(nombreResult.error))
        }

        val descResult = validateDescripcion(tipoPenalidad.descripcion)
        if (!descResult.isValid) {
            return Result.failure(IllegalArgumentException(descResult.error))
        }

        val puntosResult = validatePuntosDescuento(tipoPenalidad.puntosDescuento.toString())
        if (!puntosResult.isValid) {
            return Result.failure(IllegalArgumentException(puntosResult.error))
        }

        return runCatching { repository.upsert(tipoPenalidad) }
    }
}
