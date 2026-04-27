package com.andres.course.codex.springboot.springapp.app.controllers;

import com.andres.course.codex.springboot.springapp.app.dtos.TransactionCreateDto;
import com.andres.course.codex.springboot.springapp.app.models.TransactionType;
import jakarta.validation.Valid;
import com.andres.course.codex.springboot.springapp.app.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.NoSuchElementException;

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
        loadFormMetadata(model, false, null);
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
            loadFormMetadata(model, false, null);
            return "transactions/form";
        }

        transactionService.save(transactionCreateDto);
        return "redirect:/transactions";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("transactionCreateDto", transactionService.findCreateDtoById(id));
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }

        loadFormOptions(model);
        loadFormMetadata(model, true, id);
        return "transactions/form";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("transactionCreateDto") TransactionCreateDto transactionCreateDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            loadFormOptions(model);
            loadFormMetadata(model, true, id);
            return "transactions/form";
        }

        try {
            transactionService.update(id, transactionCreateDto);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }

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

    private void loadFormMetadata(Model model, boolean editMode, Long id) {
        model.addAttribute("pageTitle", editMode ? "Editar transaccion" : "Nueva transaccion");
        model.addAttribute("formTitle", editMode ? "Editar transaccion" : "Nueva transaccion");
        model.addAttribute(
                "formDescription",
                editMode
                        ? "Actualiza la informacion del movimiento seleccionado."
                        : "Formulario base para crear un nuevo movimiento."
        );
        model.addAttribute("submitLabel", editMode ? "Actualizar transaccion" : "Guardar transaccion");
        model.addAttribute("formAction", editMode ? "/transactions/" + id : "/transactions");
    }
}
