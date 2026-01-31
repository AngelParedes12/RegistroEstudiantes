package edu.ucne.registroestudiantes.domain.repository

import edu.ucne.registroestudiantes.domain.model.TipoPenalidad
import kotlinx.coroutines.flow.Flow

interface TipoPenalidadRepository {
    fun observePenalidades(): Flow<List<TipoPenalidad>>
    suspend fun getTipoPenalidad(id: Int): TipoPenalidad?
    suspend fun upsert(tipoPenalidad: TipoPenalidad): Int
    suspend fun delete(id: Int)
    suspend fun existsNombre(nombre: String, excludeId: Int): Boolean
}