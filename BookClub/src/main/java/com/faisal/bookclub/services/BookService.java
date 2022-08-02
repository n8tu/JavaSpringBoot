package com.faisal.bookclub.services;

import com.faisal.bookclub.models.Book;
import com.faisal.bookclub.models.User;
import com.faisal.bookclub.repositories.BookRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookService{

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    public void createBook(Book b){
        bookRepository.save(b);
    }

    public Book findBook(long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }else{
            return null;
        }
    }

    public void updateBook(long id , Book b){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            book.get().setAuthor(b.getAuthor());
            book.get().setThoughts(b.getThoughts());
            book.get().setTitle(b.getTitle());
            bookRepository.save(book.get());
        }
    }

    public void borrowBook(User user, long book_id){
        Optional<Book> book = bookRepository.findById(book_id);
        if(book.isPresent()){
            if(book.get().getBorrow() == null) {
                book.get().setBorrow(user);
                bookRepository.save(book.get());
            }
        }
    }

    public void returnBook(User user , long book_id){
        Optional<Book> book = bookRepository.findById(book_id);
        if(book.isPresent()){
            if(book.get().getBorrow().getId() == user.getId()){
                book.get().setBorrow(null);
                bookRepository.save(book.get());
            }
        }
    }

    public void deleteBook(User user , long book_id){
        Optional<Book> book = bookRepository.findById(book_id);
        if(book.isPresent()){
            if(book.get().getOwner().getId() == user.getId()){
                bookRepository.delete(book.get());
            }
        }
    }

}
