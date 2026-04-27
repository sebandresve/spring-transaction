package com.andres.course.codex.springboot.springapp.app.controllers;

import com.andres.course.codex.springboot.springapp.app.dtos.TransactionCreateDto;
import com.andres.course.codex.springboot.springapp.app.models.TransactionType;
import jakarta.validation.Valid;
import com.andres.course.codex.springboot.springapp.app.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        model.addAttribute("pageTitle", "Movimientos");
        return "transactions/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("transactionCreateDto", new TransactionCreateDto(
                "",
                TransactionType.INCOME,
                null,
                LocalDate.now()
        ));
        loadFormOptions(model);
        model.addAttribute("pageTitle", "Nueva transaccion");
        return "transactions/form";
    }

    @PostMapping
    public String save(
            @Valid @ModelAttribute("transactionCreateDto") TransactionCreateDto transactionCreateDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            loadFormOptions(model);
            model.addAttribute("pageTitle", "Nueva transaccion");
            return "transactions/form";
        }

        transactionService.save(transactionCreateDto);
        return "redirect:/transactions";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        transactionService.deleteById(id);
        return "redirect:/transactions";
    }

    private void loadFormOptions(Model model) {
        model.addAttribute("transactionTypes", Arrays.asList(TransactionType.values()));
    }
}
