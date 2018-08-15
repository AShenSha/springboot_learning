package com.shensha.springbootweb.com.shensha.springbootweb.web;

import com.shensha.springbootweb.com.shensha.springbootweb.entity.Book;
import com.shensha.springbootweb.com.shensha.springbootweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/findAll")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Book getBook(Long id){
        return bookService.findById(id);
    }

    /**
     * 创建 Book
     * 处理 "/book/create" 的 POST 请求，用来新建 Book 信息
     * 通过 @RequestBody 绑定实体参数，也通过 @RequestParam 传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Book postBook(@RequestBody Book book) {
        return bookService.insertByBook(book);
    }

    /**
     * 更新 Book
     * 处理 "/update" 的 PUT 请求，用来更新 Book 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Book putBook(@RequestBody Book book) {
        return bookService.update(book);
    }

    /**
     * 删除 Book
     * 处理 "/book/{id}" 的 GET 请求，用来删除 Book 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Book deleteBook(@PathVariable Long id) {
        return bookService.delete(id);
    }
}
