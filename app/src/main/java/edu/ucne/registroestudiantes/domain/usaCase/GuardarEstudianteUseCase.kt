package edu.ucne.registroestudiantes.domain.usaCase

import edu.ucne.registroestudiantes.domain.model.Estudiante
import edu.ucne.registroestudiantes.domain.repository.EstudianteRepository

class GuardarEstudianteUseCase(
    private val repository: EstudianteRepository
) {
    suspend operator fun invoke(estudiante: Estudiante): Boolean {

        if (repository.existeNombre(estudiante.nombres)) {
            return false
        }

        repository.insertar(
            nombres = estudiante.nombres,
            email = estudiante.email,
            edad = estudiante.edad
        )
        return true
    }
}