package com.andres.course.codex.springboot.springapp.app.repositories;

import com.andres.course.codex.springboot.springapp.app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
