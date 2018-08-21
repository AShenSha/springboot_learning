package com.shensha.springbootpagingsorting.dao;

import com.shensha.springbootpagingsorting.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookDao extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {
}
