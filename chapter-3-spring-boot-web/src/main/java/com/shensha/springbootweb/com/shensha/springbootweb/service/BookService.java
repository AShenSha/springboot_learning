package com.shensha.springbootweb.com.shensha.springbootweb.service;

import com.shensha.springbootweb.com.shensha.springbootweb.entity.Book;

import java.util.List;

public interface BookService {
    /**
     * 查询所有
     * @return
     */
    List<Book> findAll();

    /**
     * 插入
     * @param book
     * @return
     */
    Book insertByBook(Book book);

    /**
     * 更新
     * @param book
     * @return
     */
    Book update(Book book);
    /**
     * 删除 Book
     *
     * @param id 编号
     */
    Book delete(Long id);

    /**
     * 获取 Book
     *
     * @param id 编号
     */
    Book findById(Long id);
}
