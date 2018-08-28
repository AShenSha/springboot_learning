package com.shensha.springbootcacheredis.service;

import com.shensha.springbootcacheredis.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Page<Book> findByPage(Pageable pageable);

    List<Book> findAll();

    Book insertByBook(Book book);

    Book update(Book book);

    Book delete(Long id);

    Book findById(Long id);

    Page<Book> findBookNoCriteria(Integer page, Integer size);

    /**
     * 这个cache不晓得什么原因,传入的参数为对象时,即便是参数相同,还是会从数据库中获取数据,不是从缓存中
     * @param page
     * @param size
     * @param bookId
     * @return
     */
    @Cacheable
    Page<Book> findBookCriteria(Integer page, Integer size, Long bookId);

}
