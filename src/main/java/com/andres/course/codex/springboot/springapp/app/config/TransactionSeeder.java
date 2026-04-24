package com.andres.course.codex.springboot.springapp.app.config;

import com.andres.course.codex.springboot.springapp.app.models.Transaction;
import com.andres.course.codex.springboot.springapp.app.models.TransactionType;
import com.andres.course.codex.springboot.springapp.app.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class TransactionSeeder {

    @Bean
    CommandLineRunner seedTransactions(TransactionRepository transactionRepository) {
        return args -> {
            if (transactionRepository.count() > 0) {
                return;
            }

            transactionRepository.saveAll(List.of(
                    buildTransaction("Sueldo mensual", TransactionType.INCOME, "1850000.00", LocalDate.of(2026, 4, 1)),
                    buildTransaction("Arriendo departamento", TransactionType.EXPRENSE, "620000.00", LocalDate.of(2026, 4, 2)),
                    buildTransaction("Supermercado quincenal", TransactionType.EXPRENSE, "94350.50", LocalDate.of(2026, 4, 3)),
                    buildTransaction("Venta de notebook usado", TransactionType.INCOME, "280000.00", LocalDate.of(2026, 4, 4)),
                    buildTransaction("Cuenta de electricidad", TransactionType.EXPRENSE, "48790.00", LocalDate.of(2026, 4, 5)),
                    buildTransaction("Freelance landing page", TransactionType.INCOME, "350000.00", LocalDate.of(2026, 4, 6)),
                    buildTransaction("Internet hogar", TransactionType.EXPRENSE, "21990.00", LocalDate.of(2026, 4, 7)),
                    buildTransaction("Cena familiar", TransactionType.EXPRENSE, "46800.00", LocalDate.of(2026, 4, 8)),
                    buildTransaction("Reembolso oficina", TransactionType.INCOME, "32750.00", LocalDate.of(2026, 4, 9)),
                    buildTransaction("Bencina", TransactionType.EXPRENSE, "65000.00", LocalDate.of(2026, 4, 10)),
                    buildTransaction("Dividendos fondo mutuo", TransactionType.INCOME, "41230.75", LocalDate.of(2026, 4, 11)),
                    buildTransaction("Farmacia", TransactionType.EXPRENSE, "18760.90", LocalDate.of(2026, 4, 12)),
                    buildTransaction("Clases particulares", TransactionType.INCOME, "120000.00", LocalDate.of(2026, 4, 13)),
                    buildTransaction("Suscripcion streaming", TransactionType.EXPRENSE, "8990.00", LocalDate.of(2026, 4, 14)),
                    buildTransaction("Almuerzo con clientes", TransactionType.EXPRENSE, "28500.00", LocalDate.of(2026, 4, 15)),
                    buildTransaction("Bono por cumplimiento", TransactionType.INCOME, "210000.00", LocalDate.of(2026, 4, 16)),
                    buildTransaction("Bapho debe 5mil F y mojitos", TransactionType.EXPRENSE, "7000.00", LocalDate.of(2026, 4, 17)),
                    buildTransaction("Venta de accesorios", TransactionType.INCOME, "57500.00", LocalDate.of(2026, 4, 18)),
                    buildTransaction("Bapho debe Aire Acondicionado", TransactionType.EXPRENSE, "60000.00", LocalDate.of(2026, 4, 19)),
                    buildTransaction("Interes cuenta ahorro", TransactionType.INCOME, "12840.35", LocalDate.of(2026, 4, 20))
            ));
        };
    }

    private Transaction buildTransaction(
            String description,
            TransactionType type,
            String amount,
            LocalDate movementDate
    ) {
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setType(type);
        transaction.setAmount(new BigDecimal(amount));
        transaction.setMovementDate(movementDate);
        return transaction;
    }
}
