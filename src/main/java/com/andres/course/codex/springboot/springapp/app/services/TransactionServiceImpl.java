package com.andres.course.codex.springboot.springapp.app.services;

import com.andres.course.codex.springboot.springapp.app.dtos.TransactionCreateDto;
import com.andres.course.codex.springboot.springapp.app.dtos.TransactionDto;
import com.andres.course.codex.springboot.springapp.app.dtos.TransactionFilterDto;
import com.andres.course.codex.springboot.springapp.app.mappers.TransactionMapper;
import com.andres.course.codex.springboot.springapp.app.models.Transaction;
import com.andres.course.codex.springboot.springapp.app.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Predicate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

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
    public Page<TransactionDto> findAll(TransactionFilterDto transactionFilterDto, Pageable pageable) {
        Pageable normalizedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(
                        Sort.Order.desc("movementDate"),
                        Sort.Order.desc("id")
                )
        );

        return transactionRepository.findAll(buildSpecification(transactionFilterDto), normalizedPageable)
                .map(transactionMapper::toDto);
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

    private Specification<Transaction> buildSpecification(TransactionFilterDto transactionFilterDto) {
        return (root, query, criteriaBuilder) -> {
            if (transactionFilterDto == null) {
                return criteriaBuilder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            if (hasText(transactionFilterDto.getDescription())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("description")),
                                "%" + transactionFilterDto.getDescription().trim().toLowerCase() + "%"
                        )
                );
            }

            if (transactionFilterDto.getType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), transactionFilterDto.getType()));
            }

            if (transactionFilterDto.getMovementDateFrom() != null) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("movementDate"),
                                transactionFilterDto.getMovementDateFrom()
                        )
                );
            }

            if (transactionFilterDto.getMovementDateTo() != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("movementDate"),
                                transactionFilterDto.getMovementDateTo()
                        )
                );
            }

            if (transactionFilterDto.getAmountMin() != null) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("amount"),
                                transactionFilterDto.getAmountMin()
                        )
                );
            }

            if (transactionFilterDto.getAmountMax() != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("amount"),
                                transactionFilterDto.getAmountMax()
                        )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
