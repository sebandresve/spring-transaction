package com.andres.course.codex.springboot.springapp.app.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionDto(
        Long id,
        String description,
        String type,
        String typeLabel,
        BigDecimal amount,
        LocalDate movementDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
