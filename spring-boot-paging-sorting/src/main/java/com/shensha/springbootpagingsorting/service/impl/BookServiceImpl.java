package com.shensha.springbootpagingsorting.service.impl;

import com.shensha.springbootpagingsorting.dao.BookDao;
import com.shensha.springbootpagingsorting.entity.Book;
import com.shensha.springbootpagingsorting.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    BookDao bookDao;

    @Override
    public Page<Book> findByPage(Pageable pageable) {
        LOGGER.info(" \n 分页查询用户："
                + " PageNumber = " + pageable.getPageNumber()
                + " PageSize = " + pageable.getPageSize());
        return bookDao.findAll(pageable);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book insertByBook(Book book) {
        LOGGER.info("新增书籍"+book);
        return bookDao.save(book);
    }

    @Override
    public Book update(Book book) {
        LOGGER.info("编辑书籍"+book);
        return bookDao.save(book);
    }

    @Override
    public Book delete(Long id) {
        Book book = bookDao.findById(id).get();
        bookDao.delete(book);
        LOGGER.info("删除书籍"+book);
        return book;
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id).get();
    }

    @Override
    public Page<Book> findBookNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return bookDao.findAll(pageable);

    }
}
