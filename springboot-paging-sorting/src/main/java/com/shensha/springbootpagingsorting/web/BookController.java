package com.shensha.springbootpagingsorting.web;

import com.shensha.springbootpagingsorting.entity.Book;
import com.shensha.springbootpagingsorting.service.BookService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;


//    @RequestMapping(method = RequestMethod.GET)
//    public String getBook(ModelMap map, Pageable pageable) {
//        map.put("bookList", bookService.findByPage(pageable));
//        return "bookPageList";
//    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String gotoBookForm(ModelMap map) {
        map.put("book", new Book());
        map.put("action", "create");
        return "bookForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String saveBook(ModelMap map, @ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            map.addAttribute("action", "create");
        }
        bookService.insertByBook(book);
        return "redirect:/books/findBookNoQuery";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books/findBookNoQuery";
    }


    @RequestMapping("/findBookNoQuery")
    public String findBookNoQuery(ModelMap modelMap, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
        Page<Book> datas = bookService.findBookNoCriteria(page, size);
        modelMap.addAttribute("datas", datas);
        return "bookList";
    }

}
