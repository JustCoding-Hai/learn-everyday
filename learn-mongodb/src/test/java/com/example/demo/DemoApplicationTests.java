package com.example.demo;

import com.example.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	MongoTemplate mongoTemplate;

	@Test
	void contextLoads() {
        List<Book> books = mongoTemplate.find(new Query(where("name").is("钢铁是怎么炼成的").and("info").is("经典")), Book.class);
        System.out.println(books.get(0));
    }

}
