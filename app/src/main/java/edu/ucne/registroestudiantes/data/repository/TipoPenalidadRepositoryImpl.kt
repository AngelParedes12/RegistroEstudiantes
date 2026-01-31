package edu.ucne.registroestudiantes.data.repository

import edu.ucne.registroestudiantes.data.local.dao.TiposPenalidadesDao
import edu.ucne.registroestudiantes.data.mapper.toDomain
import edu.ucne.registroestudiantes.data.mapper.toEntity
import edu.ucne.registroestudiantes.domain.model.TipoPenalidad
import edu.ucne.registroestudiantes.domain.repository.TipoPenalidadRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TipoPenalidadRepositoryImpl @Inject constructor(
    private val localDataSource: TiposPenalidadesDao
) : TipoPenalidadRepository {

    override fun observePenalidades(): Flow<List<TipoPenalidad>> {
        return localDataSource.observeAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getTipoPenalidad(id: Int): TipoPenalidad? {
        return localDataSource.getById(id)?.toDomain()
    }

    override suspend fun upsert(TipoPenalidad: TipoPenalidad): Int {
        val newId = localDataSource.upsert(TipoPenalidad.toEntity()).toInt()
        return if (TipoPenalidad.penalidadId == 0) newId else TipoPenalidad.penalidadId
    }

    override suspend fun delete(id: Int) {
        localDataSource.deleteById(id)
    }
}
