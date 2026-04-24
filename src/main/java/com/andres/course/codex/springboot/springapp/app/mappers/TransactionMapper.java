package com.andres.course.codex.springboot.springapp.app.mappers;

import com.andres.course.codex.springboot.springapp.app.dtos.TransactionCreateDto;
import com.andres.course.codex.springboot.springapp.app.dtos.TransactionDto;
import com.andres.course.codex.springboot.springapp.app.models.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getType().name(),
                transaction.getType().getValue(),
                transaction.getAmount(),
                transaction.getMovementDate(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }

    public Transaction toEntity(TransactionCreateDto transactionCreateDto) {
        Transaction transaction = new Transaction();
        transaction.setDescription(transactionCreateDto.description());
        transaction.setType(transactionCreateDto.type());
        transaction.setAmount(transactionCreateDto.amount());
        transaction.setMovementDate(transactionCreateDto.movementDate());
        return transaction;
    }
}
