package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

/**
 * @author Ethan Huang
 * @create 2021-01-04 15:22
 */
@Repository
public class BookMongoDbDao extends BaseMongoDbDao<Book> {
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
