package edu.ucne.registroestudiantes.domain.validation

data class ValidationResult(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombre(nombre: String): ValidationResult {
    return when {
        nombre.isBlank() -> ValidationResult(false, "El nombre no puede estar vacío")
        nombre.length < 3 -> ValidationResult(false, "El nombre debe tener al menos 3 caracteres")
        else -> ValidationResult(true)
    }
}

fun validateDescripcion(descripcion: String): ValidationResult {
    return when {
        descripcion.isBlank() -> ValidationResult(false, "La descripción no puede estar vacía")
        descripcion.length < 3 -> ValidationResult(false, "La descripción debe tener al menos 3 caracteres")
        else -> ValidationResult(true)
    }
}

fun validatePuntosDescuento(value: String): ValidationResult {
    return when {
        value.isBlank() -> ValidationResult(false, "Los puntos no pueden estar vacíos")
        value.toIntOrNull() == null -> ValidationResult(false, "Los puntos deben ser un número")
        value.toInt() <= 0 -> ValidationResult(false, "Los puntos deben ser mayor a 0")
        else -> ValidationResult(true)
    }
}