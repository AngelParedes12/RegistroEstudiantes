package edu.ucne.registroestudiantes.domain.model

data class TipoPenalidad(
    val penalidadId: Int = 0,
    val nombre: String,
    val descripcion: String,
    val puntosDescuento: Int

)