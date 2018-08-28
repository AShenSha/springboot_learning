package com.shensha.springbootcacheredis.dao;

import com.shensha.springbootcacheredis.entity.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//@CacheConfig(cacheNames = "books")
public interface BookDao extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {
}
