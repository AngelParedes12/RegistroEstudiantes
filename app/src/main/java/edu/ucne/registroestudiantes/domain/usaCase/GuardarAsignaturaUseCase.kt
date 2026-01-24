package edu.ucne.registroestudiantes.domain.usaCase

import android.database.sqlite.SQLiteConstraintException
import edu.ucne.registroestudiantes.data.local.entities.AsignaturaEntity
import edu.ucne.registroestudiantes.domain.repository.AsignaturaRepository
import javax.inject.Inject

sealed interface GuardarAsignaturaUseCase {
    data object Success : GuardarAsignaturaUseCase
    data class Error(val message: String) : GuardarAsignaturaUseCase
}

class SaveAsignaturaUseCase @Inject constructor(
    private val repo: AsignaturaRepository
) {
    suspend operator fun invoke(entity: AsignaturaEntity): GuardarAsignaturaUseCase {
        val codigo = entity.codigo.trim()
        val nombre = entity.nombre.trim()
        val aula = entity.aula.trim()
        val creditos = entity.creditos

        if (codigo.isBlank() || nombre.isBlank() || aula.isBlank() || creditos <= 0) {
            return GuardarAsignaturaUseCase.Error("Todos los campos son obligatorios.")
        }

        if (repo.existsNombre(nombre)) {
            return GuardarAsignaturaUseCase.Error("Ya existe una asignatura registrada con este nombre")
        }

        return try {
            repo.insert(
                entity.copy(codigo = codigo, nombre = nombre, aula = aula, creditos = creditos)
            )
            GuardarAsignaturaUseCase.Success
        } catch (_: SQLiteConstraintException) {
            GuardarAsignaturaUseCase.Error("Ya existe una asignatura registrada con este nombre")
        } catch (_: Exception) {
            GuardarAsignaturaUseCase.Error("Ocurrió un error guardando la asignatura.")
        }
    }
}
