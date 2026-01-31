package edu.ucne.registroestudiantes.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TiposPenalidades",
    indices = [
        Index(value = ["nombre"], unique = true)
    ]
)
data class TipoPenalidadEntity(
    @PrimaryKey(autoGenerate = true)
    val penalidadId: Int = 0,
    val nombre: String,
    val descripcion: String,
    val puntosDescuento: Int
)