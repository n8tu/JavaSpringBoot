package com.faisal.booksapi.services;

// ...
import com.faisal.booksapi.models.Book;
import com.faisal.booksapi.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    // adding the book repository as a dependency
private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }

    public void deleteBook(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            bookRepository.delete(optionalBook.get());
        }
    }

    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setDescription(desc);
            book.setTitle(title);
            book.setLanguage(lang);
            book.setNumberOfPages(numOfPages);
            bookRepository.save(book);
            return book;
        }else{
            return null;
        }
    }
}

