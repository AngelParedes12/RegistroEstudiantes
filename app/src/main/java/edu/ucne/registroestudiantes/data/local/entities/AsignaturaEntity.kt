package edu.ucne.registroestudiantes.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "asignaturas",
    indices = [Index(value = ["Nombre"], unique = true)]
)
data class AsignaturaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AsignaturaId")
    val asignaturaId: Int = 0,

    @ColumnInfo(name = "Codigo")
    val codigo: String,

    @ColumnInfo(name = "Nombre")
    val nombre: String,

    @ColumnInfo(name = "Aula")
    val aula: String,

    @ColumnInfo(name = "Creditos")
    val creditos: Int
)
