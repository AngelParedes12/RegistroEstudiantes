package edu.ucne.registroestudiantes.presentation.tareas.asignaturas.list

import edu.ucne.registroestudiantes.data.local.entities.AsignaturaEntity

data class ListAsignaturaUIState(
    val asignaturas: List<AsignaturaEntity> = emptyList()
)