package com.example.demo.service;

import com.example.demo.dao.BookMongoDbDao;
import com.example.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Ethan Huang
 * @create 2021-01-04 15:27
 */
@Service
public class BookMongoDbService {

    @Autowired
    private BookMongoDbDao bookMongoDbDao;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    private static final Logger LOGGER= LoggerFactory.getLogger(BookMongoDbService.class);

    /**
     * 保存对象
     *
     * @param book
     * @return
     */
    public String saveObj(Book book) {
        LOGGER.info("--------------------->[MongoDB save start]");
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        /*********调用bookMongoDbDao父类中的添加方法************/
        bookMongoDbDao.save(book);
        return "添加成功";
    }

    public String insertObj(Book book) {
        LOGGER.info("--------------------->[MongoDB insert start]");
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        //mongoTemplate.insert(book);
        //mongoTemplate.insert(book,"book_ref");

        bookMongoDbDao.insert(book);
        return "添加成功";
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Book> findAll() {
        LOGGER.info("--------------------->[MongoDB find start]");
        //return mongoTemplate.findAll(Book.class);
        //return mongoTemplate.findAll(Book.class,"book_ref");

        Book book = new Book();
        return bookMongoDbDao.queryList(book);
    }


    /***
     * 根据id查询
     * @param id
     * @return
     */
    public Book getBookById(Integer id) {
        LOGGER.info("--------------------->[MongoDB find start]");
        Query query = new Query(Criteria.where("_id").is(id));
        //return mongoTemplate.findOne(query, Book.class);

        return bookMongoDbDao.queryById(id);
    }

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    public Book getBookByName(String name) {
        LOGGER.info("--------------------->[MongoDB find start]");
        Query query = new Query(Criteria.where("name").is(name));
        //return mongoTemplate.findOne(query, Book.class);

        Book book = new Book();
        book.setName(name);
        return bookMongoDbDao.queryOne(book);
    }

    /**
     * 更新对象
     *
     * @param book
     * @return
     */
    public String updateBook(Book book) {
        LOGGER.info("--------------------->[MongoDB update start]");
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Update update = new Update().set("publish", book.getPublish())
                .set("info", book.getInfo())
                .set("updateTime", new Date());
        //updateFirst 更新查询返回结果集的第一条
        //mongoTemplate.updateFirst(query, update, Book.class);
        //updateMulti 更新查询返回结果集的全部
        //mongoTemplate.updateMulti(query,update,Book.class);
        //upsert 更新对象不存在则去添加
        //mongoTemplate.upsert(query,update,Book.class);

        Book srcBook = new Book();
        srcBook.setId(book.getId());
        bookMongoDbDao.updateFirst(srcBook, book);
        return "success";
    }

    /***
     * 删除对象
     * @param book
     * @return
     */
    public String deleteBook(Book book) {
        LOGGER.info("--------------------->[MongoDB delete start]");
        //mongoTemplate.remove(book);

        int a = bookMongoDbDao.delete(book);
        System.out.println(a);
        return "success";
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public String deleteBookById(Integer id) {
        LOGGER.info("--------------------->[MongoDB delete start]");
        //findOne
        //Book book = getBookById(id);
        //delete
        //deleteBook(book);
        bookMongoDbDao.deleteById(id);
        return "success";
    }
}
