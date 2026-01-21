package edu.ucne.registroestudiantes.domain.repository

import edu.ucne.registroestudiantes.domain.model.Estudiante
interface EstudianteRepository {
    suspend fun obtenerTodos(): List<Estudiante>
    suspend fun insertar(
        nombres: String,
        email: String,
        edad: Int
    )
    suspend fun existeNombre(nombre: String): Boolean
    suspend fun eliminar(estudiante: Estudiante)
}