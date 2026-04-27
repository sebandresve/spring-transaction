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
        apply(transaction, transactionCreateDto);
        return transaction;
    }

    public TransactionCreateDto toCreateDto(Transaction transaction) {
        return new TransactionCreateDto(
                transaction.getDescription(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getMovementDate()
        );
    }

    public void apply(Transaction transaction, TransactionCreateDto transactionCreateDto) {
        transaction.setDescription(transactionCreateDto.description());
        transaction.setType(transactionCreateDto.type());
        transaction.setAmount(transactionCreateDto.amount());
        transaction.setMovementDate(transactionCreateDto.movementDate());
    }
}
