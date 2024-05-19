package com.fantasyfreakz.fplc.controller;

import com.fantasyfreakz.fplc.model.Book;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    List<Book> bookList = new ArrayList<>();

    @GetMapping("")
    public String greetings(){
        return "Welcome to my first spring boot application";
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
        bookList.add(book);
        return book;
    }

    @GetMapping("/allBooks")
    public List<Book> getAllBooks(){
        return bookList;
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book updatedBook){
        for (int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getId().equals(id)) {
                bookList.set(i, updatedBook);
                break;
            }
        }
        return updatedBook;
    }

    @DeleteMapping("/delete/{id}")
    public Book deleteBook(@PathVariable Integer id){
        Book book = null;
        for (int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getId().equals(id)) {
                book = bookList.get(i);
                bookList.remove(i);
                break;
            }
        }
        return book;
    }

}
