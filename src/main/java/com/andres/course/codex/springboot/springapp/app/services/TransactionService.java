package com.andres.course.codex.springboot.springapp.app.services;

import com.andres.course.codex.springboot.springapp.app.dtos.TransactionCreateDto;
import com.andres.course.codex.springboot.springapp.app.dtos.TransactionDto;
import com.andres.course.codex.springboot.springapp.app.dtos.TransactionFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<TransactionDto> findAll(TransactionFilterDto transactionFilterDto, Pageable pageable);

    TransactionCreateDto findCreateDtoById(Long id);

    TransactionDto save(TransactionCreateDto transactionCreateDto);

    TransactionDto update(Long id, TransactionCreateDto transactionCreateDto);

    void deleteById(Long id);
}
