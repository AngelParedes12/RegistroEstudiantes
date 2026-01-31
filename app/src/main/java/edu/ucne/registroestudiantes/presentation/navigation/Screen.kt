package edu.ucne.registroestudiantes.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object ListEstudiantes : Screen

    @Serializable
    data class EditEstudiante(val estudianteId: Int? = null) : Screen

    @Serializable
    data object ListAsignaturas : Screen

    @Serializable
    data class EditAsignatura(val asignaturaId: Int? = null) : Screen

    @Serializable
    data object ListTipoPenalidad : Screen

    @Serializable
    data class EditTipoPenalidad(val penalidadId: Int? = null) : Screen
}