package com.shensha.springbootpagingsorting.service;

import com.shensha.springbootpagingsorting.entity.Book;
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

    Page<Book> findBookNoCriteria(Integer page,Integer size);
//    Page<Book> findBookCriteria(Integer page,Integer size,BookQuery bookQuery);

}
