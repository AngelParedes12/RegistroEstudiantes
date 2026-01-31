package edu.ucne.registroestudiantes.data.mapper

import edu.ucne.registroestudiantes.data.local.entities.TipoPenalidadEntity
import edu.ucne.registroestudiantes.domain.model.TipoPenalidad

fun TipoPenalidadEntity.toDomain() = TipoPenalidad(
    penalidadId = penalidadId,
    nombre = nombre,
    descripcion = descripcion,
    puntosDescuento = puntosDescuento
)

fun TipoPenalidad.toEntity() = TipoPenalidadEntity(
    penalidadId = penalidadId,
    nombre = nombre,
    descripcion = descripcion,
    puntosDescuento = puntosDescuento
)
