package edu.ucne.registroestudiantes.data.repository

import edu.ucne.registroestudiantes.data.local.dao.EstudianteDao
import edu.ucne.registroestudiantes.data.local.entities.EstudianteEntity
import edu.ucne.registroestudiantes.data.mapper.toDomain
import edu.ucne.registroestudiantes.data.mapper.toEntity
import edu.ucne.registroestudiantes.domain.model.Estudiante
import edu.ucne.registroestudiantes.domain.repository.EstudianteRepository
import javax.inject.Inject

class EstudianteRepositoryImpl @Inject constructor(
    private val dao: EstudianteDao
) : EstudianteRepository {
    override suspend fun obtenerTodos(): List<Estudiante> {
        return dao.obtenerTodos().map { it.toDomain() }
    }
    override suspend fun insertar(
        nombres: String,
        email: String,
        edad: Int
    ) {
        dao.insertar(
            EstudianteEntity(
                nombres = nombres,
                email = email,
                edad = edad
            )
        )
    }
    override suspend fun existeNombre(nombre: String): Boolean {
        return dao.existeNombre(nombre) > 0
    }
    override suspend fun eliminar(estudiante: Estudiante) {
        dao.eliminar(estudiante.toEntity())
    }
}