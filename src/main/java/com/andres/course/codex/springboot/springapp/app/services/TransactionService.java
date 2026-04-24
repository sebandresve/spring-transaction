package com.andres.course.codex.springboot.springapp.app.services;

import com.andres.course.codex.springboot.springapp.app.dtos.TransactionCreateDto;
import com.andres.course.codex.springboot.springapp.app.dtos.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> findAll();

    TransactionDto save(TransactionCreateDto transactionCreateDto);
}
