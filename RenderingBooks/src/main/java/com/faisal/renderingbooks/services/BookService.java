package com.faisal.renderingbooks.services;

import com.faisal.renderingbooks.models.Book;
import com.faisal.renderingbooks.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    public Book createBook(Book b){
        return bookRepository.save(b);
    }

    public Book updateBook(long id,Book b){

        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            book.get().setTitle(b.getTitle());
            book.get().setDescription(b.getDescription());
            book.get().setNumOfPages(b.getNumOfPages());
            return bookRepository.save(book.get());
        }
        return null;
    }

    public String deleteBook(long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            bookRepository.delete(book.get());
            return "Book Deleted!";
        }
        return "Book Not Found!";
    }

    public Book getBook(long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        return null;
    }
}
