package com.andres.course.codex.springboot.springapp.app.dtos;

import com.andres.course.codex.springboot.springapp.app.models.TransactionType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionCreateDto(
        @NotBlank(message = "La descripcion es obligatoria.")
        @Size(min = 3, max = 255, message = "La descripcion debe tener entre 3 y 255 caracteres.")
        String description,
        @NotNull(message = "El tipo es obligatorio.")
        TransactionType type,
        @NotNull(message = "El monto es obligatorio.")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor que cero.")
        @Digits(integer = 17, fraction = 2, message = "El monto debe tener hasta 2 decimales.")
        BigDecimal amount,
        @NotNull(message = "La fecha de movimiento es obligatoria.")
        @PastOrPresent(message = "La fecha de movimiento no puede ser futura.")
        LocalDate movementDate
) {
}
