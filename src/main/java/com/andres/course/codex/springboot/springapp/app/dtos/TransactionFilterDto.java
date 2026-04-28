package com.andres.course.codex.springboot.springapp.app.dtos;

import com.andres.course.codex.springboot.springapp.app.models.TransactionType;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionFilterDto {

    private String description;
    private TransactionType type;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate movementDateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate movementDateTo;
    private BigDecimal amountMin;
    private BigDecimal amountMax;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getMovementDateFrom() {
        return movementDateFrom;
    }

    public void setMovementDateFrom(LocalDate movementDateFrom) {
        this.movementDateFrom = movementDateFrom;
    }

    public LocalDate getMovementDateTo() {
        return movementDateTo;
    }

    public void setMovementDateTo(LocalDate movementDateTo) {
        this.movementDateTo = movementDateTo;
    }

    public BigDecimal getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(BigDecimal amountMin) {
        this.amountMin = amountMin;
    }

    public BigDecimal getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(BigDecimal amountMax) {
        this.amountMax = amountMax;
    }

    public boolean hasActiveFilters() {
        return hasText(description)
                || type != null
                || movementDateFrom != null
                || movementDateTo != null
                || amountMin != null
                || amountMax != null;
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
