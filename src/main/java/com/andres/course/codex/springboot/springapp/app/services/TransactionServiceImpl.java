package com.andres.course.codex.springboot.springapp.app.services;

import com.andres.course.codex.springboot.springapp.app.dtos.TransactionCreateDto;
import com.andres.course.codex.springboot.springapp.app.dtos.TransactionDto;
import com.andres.course.codex.springboot.springapp.app.mappers.TransactionMapper;
import com.andres.course.codex.springboot.springapp.app.models.Transaction;
import com.andres.course.codex.springboot.springapp.app.repositories.TransactionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            TransactionMapper transactionMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public List<TransactionDto> findAll() {
        Sort sort = Sort.by(
                Sort.Order.desc("movementDate"),
                Sort.Order.desc("id")
        );

        return transactionRepository.findAll(sort)
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionCreateDto findCreateDtoById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transaccion no encontrada: " + id));
        return transactionMapper.toCreateDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDto save(TransactionCreateDto transactionCreateDto) {
        Transaction transaction = transactionMapper.toEntity(transactionCreateDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(savedTransaction);
    }

    @Override
    @Transactional
    public TransactionDto update(Long id, TransactionCreateDto transactionCreateDto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transaccion no encontrada: " + id));
        transactionMapper.apply(transaction, transactionCreateDto);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(updatedTransaction);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!transactionRepository.existsById(id)) {
            return;
        }

        transactionRepository.deleteById(id);
    }
}
