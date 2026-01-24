package edu.ucne.registroestudiantes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.registroestudiantes.data.local.entities.AsignaturaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsignaturaDao {

    @Query("SELECT * FROM asignaturas ORDER BY Nombre")
    fun getAll(): Flow<List<AsignaturaEntity>>

    @Query("SELECT COUNT(*) FROM asignaturas WHERE Nombre = :nombre LIMIT 1")
    suspend fun countByNombre(nombre: String): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: AsignaturaEntity): Long

    @Query("DELETE FROM asignaturas WHERE AsignaturaId = :asignaturaId")
    suspend fun deleteById(asignaturaId: Int): Int
}