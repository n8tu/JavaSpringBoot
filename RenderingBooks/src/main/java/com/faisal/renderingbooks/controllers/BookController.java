package com.faisal.renderingbooks.controllers;

import com.faisal.renderingbooks.models.Book;
import com.faisal.renderingbooks.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books/{id}")
    public String showBook(
            Model model,
            @PathVariable(value = "id") long id
    ){
        Book book = bookService.getBook(id);
        model.addAttribute("book",book);
        return "show_book.jsp";
    }
}
