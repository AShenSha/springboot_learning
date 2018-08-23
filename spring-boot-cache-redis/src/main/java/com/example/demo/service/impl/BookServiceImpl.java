package com.example.demo.service.impl;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("bookService")
@CacheConfig(cacheNames = "books")
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

    /**
     * @CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，
     * 而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
     * @param book
     * @return
     */
    @CachePut(key = "#p0.id")
    @Override
    public Book update(Book book) {
        LOGGER.info("编辑书籍"+book);
        return bookDao.save(book);
    }

    /**
     * @CacheEvict标记的方法会在方法执行前或者执行后移除SpringCache中的某些元素。
     * 可以指定的属性有value、key、condition、allEntries和beforeInvocation
     * allEntries是boolean类型，表示是否需要清除缓存中的所有元素,当指定了allEntries为true时，Spring Cache将忽略指定的key
     *
     * @param id
     * @return
     */
    @CacheEvict(key = "#p0")
    @Override
    public Book delete(Long id) {
        Book book = bookDao.findById(id).get();
        bookDao.delete(book);
        LOGGER.info("删除书籍"+book);
        return book;
    }

    @Cacheable(key = "#p0")
    @Override
    public Book findById(Long id) {
        return bookDao.findById(id).get();
    }

    /**
     * @Cacheable可以标记在一个方法上，也可以标记在一个类上
     * 当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的。
     * @param page
     * @param size
     * @return
     */
//    @Cacheable(key = "#p0")
    @Override
    public Page<Book> findBookNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return bookDao.findAll(pageable);

    }

    @Cacheable(key = "#p0")
    @Override
    public Page<Book> findBookCriteria(Integer page, Integer size, Book bookQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Book> bookPage = bookDao.findAll(new Specification<Book>(){
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null!=bookQuery.getName()&&!"".equals(bookQuery.getName())){
                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), bookQuery.getName()));
                }
                if(null!=bookQuery.getId()&&!"".equals(bookQuery.getId())){
                    list.add(criteriaBuilder.equal(root.get("id").as(String.class), bookQuery.getId()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return bookPage;
    }
}
