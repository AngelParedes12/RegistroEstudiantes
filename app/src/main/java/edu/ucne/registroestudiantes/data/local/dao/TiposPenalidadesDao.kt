package edu.ucne.registroestudiantes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.registroestudiantes.data.local.entities.TipoPenalidadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TiposPenalidadesDao {

    @Upsert
    suspend fun upsert(entity: TipoPenalidadEntity): Long
    @Delete
    suspend fun delete(entity: TipoPenalidadEntity)

    @Query("SELECT * FROM TiposPenalidades ORDER BY penalidadId DESC")
    fun observeAll(): Flow<List<TipoPenalidadEntity>>

    @Query("SELECT * FROM TiposPenalidades WHERE penalidadId = :id")
    suspend fun getById(id: Int): TipoPenalidadEntity?

    @Query("DELETE FROM TiposPenalidades WHERE penalidadId = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT COUNT(*) FROM TiposPenalidades WHERE Nombre = :nombre AND penalidadId != :excludeId")
    suspend fun countByNombre(nombre: String, excludeId: Int): Int
}
