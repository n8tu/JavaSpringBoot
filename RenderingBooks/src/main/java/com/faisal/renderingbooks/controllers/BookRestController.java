package com.faisal.renderingbooks.controllers;

import com.faisal.renderingbooks.models.Book;
import com.faisal.renderingbooks.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/")
    public List<Book> index(){
        return bookService.allBooks();
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public Book createBook(
            @RequestParam(value="title") String title,
            @RequestParam(value="description") String description,
            @RequestParam(value="numOfPages") int pages
    ){

        return bookService.createBook(new Book(title,description,pages));
    }

    @RequestMapping(value="/update/{id}",method = RequestMethod.PUT)
    public Book updateBook(
            @PathVariable(value = "id") long id,
            @RequestParam(value="title") String title,
            @RequestParam(value="description") String description,
            @RequestParam(value="numOfPages") int pages
    ){

        return bookService.updateBook(id,new Book(title,description,pages));
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public String deleteBook(
            @PathVariable(value="id") long id
    ){
        return bookService.deleteBook(id);
    }





}
