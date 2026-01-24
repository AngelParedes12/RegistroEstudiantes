package edu.ucne.registroestudiantes.domain.repository

import edu.ucne.registroestudiantes.data.local.entities.AsignaturaEntity
import kotlinx.coroutines.flow.Flow

interface AsignaturaRepository {
    fun getAll(): Flow<List<AsignaturaEntity>>
    suspend fun existsNombre(nombre: String): Boolean
    suspend fun insert(entity: AsignaturaEntity)
    suspend fun deleteById(asignaturaId: Int)
}