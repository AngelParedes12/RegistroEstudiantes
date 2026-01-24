package edu.ucne.registroestudiantes.data.repository

import android.database.sqlite.SQLiteConstraintException
import edu.ucne.registroestudiantes.data.local.dao.AsignaturaDao
import edu.ucne.registroestudiantes.data.local.entities.AsignaturaEntity
import edu.ucne.registroestudiantes.domain.repository.AsignaturaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AsignaturaRepositoryImpl @Inject constructor(
    private val dao: AsignaturaDao
) : AsignaturaRepository {

    override fun getAll(): Flow<List<AsignaturaEntity>> = dao.getAll()

    override suspend fun existsNombre(nombre: String): Boolean =
        dao.countByNombre(nombre.trim()) > 0

    override suspend fun insert(entity: AsignaturaEntity) {
        try {
            dao.insert(entity)
        } catch (e: SQLiteConstraintException) {
            throw e
        }
    }

    override suspend fun deleteById(asignaturaId: Int) {
        dao.deleteById(asignaturaId)
    }
}