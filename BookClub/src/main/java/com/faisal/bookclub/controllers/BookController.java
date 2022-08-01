package com.faisal.bookclub.controllers;

import com.faisal.bookclub.models.Book;
import com.faisal.bookclub.services.BookService;
import com.faisal.bookclub.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    private final UserController userController;

    public BookController(BookService bookService, UserService userService, UserController userController) {
        this.bookService = bookService;
        this.userService = userService;
        this.userController = userController;
    }

    @RequestMapping("/books")
    public String booksDashboard(
            Model model,
            HttpSession session
    ){
        if(!userController.isLogin(session)){
            return "redirect:/login";
        }

        model.addAttribute("books",bookService.allBooks());
        model.addAttribute("user",userService.findUser(
                (long) session.getAttribute("user_id")
        ));
        return "books_dashboard.jsp";
    }

    @RequestMapping(value = "/books/new", method = RequestMethod.GET)
    public String newBook(
            Model model,
            @ModelAttribute("book") Book book,
            HttpSession session
    ){
        if(!userController.isLogin(session)){
            return "redirect:/login";
        }
        model.addAttribute("user",userService.findUser(
                (long) session.getAttribute("user_id")
        ));
        return "new_book.jsp";
    }

    @RequestMapping(value = "/books/new", method = RequestMethod.POST)
    public String processCreateBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult result,
            RedirectAttributes attr,
            HttpSession session
    ){
        if(!userController.isLogin(session)){
            return "redirect:/login";
        }
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/books/new";
        }else{
            bookService.createBook(book);
            return "redirect:/books";
        }
    }

    @RequestMapping("/books/{id}")
    public String showBook(
            @PathVariable(value="id") long id,
            Model model,
            HttpSession session
    ){
        if(!userController.isLogin(session)){
            return "redirect:/login";
        }
        model.addAttribute("book",bookService.findBook(id));
        model.addAttribute("user",userService.findUser(
                (long) session.getAttribute("user_id")
        ));
        return "show_book.jsp";
    }

    @RequestMapping(value = "/books/{id}/edit" , method = RequestMethod.GET)
    public String editBook(
            @PathVariable(value = "id") long id,
            Model model,
            @ModelAttribute("_book") Book _book,
            HttpSession session
    ){
        if(!userController.isLogin(session)){
            return "redirect:/login";
        }
        Book book = bookService.findBook(id);
        if(!userController.isValid(book,session)){
            return "redirect:/books";
        }
        model.addAttribute("book",book);
        return "edit_book.jsp";
    }

    @RequestMapping(value = "/books/{id}/edit" , method = RequestMethod.POST)
    public String updateBook(
            @PathVariable(value = "id") long id,
            @Valid @ModelAttribute("_book") Book book,
            BindingResult result,
            RedirectAttributes attr,
            HttpSession session
    ){
        if(!userController.isLogin(session)){
            return "redirect:/login";
        }
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return String.format("redirect:/books/%d/edit",id);
        }else{
            if(!userController.isValid(bookService.findBook(id),session)){
                return "redirect:/books";
            }
            bookService.updateBook(id,book);
            return "redirect:/books";
        }
    }





}
