package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookMongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ethan Huang
 * @create 2021-01-04 15:21
 */
@RestController
@RequestMapping(value = "/api/mongo")
public class BookController {
    @Autowired
    private BookMongoDbService mongoDbService;

    @PostMapping("/save")
    public String saveObj(@RequestBody Book book) {
        return mongoDbService.saveObj(book);
    }

    @PostMapping("/insert")
    public String insertObj(@RequestBody Book book) {
        try {
            return mongoDbService.insertObj(book);
        } catch (Exception e) {
            return "Exception";
        }
    }

    @GetMapping("/findAll")
    public List<Book> findAll() {
        return mongoDbService.findAll();
    }

    @GetMapping("/findOne")
    public Book findOne(@RequestParam Integer id) {
        return mongoDbService.getBookById(id);
    }

    @GetMapping("/findOneByName")
    public Book findOneByName(@RequestParam String name) {
        return mongoDbService.getBookByName(name);
    }

    @PutMapping("/update")
    public String update(@RequestBody Book book) {
        return mongoDbService.updateBook(book);
    }

    @DeleteMapping("/delOne")
    public String delOne(@RequestBody Book book) {
        return mongoDbService.deleteBook(book);
    }

    @DeleteMapping("/delById")
    public String delById(@RequestParam Integer id) {
        return mongoDbService.deleteBookById(id);
    }
}
