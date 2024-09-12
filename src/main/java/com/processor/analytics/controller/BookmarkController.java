package com.processor.analytics.controller;

import com.processor.analytics.models.BookmarkStock;
import com.processor.analytics.repository.BookmarkRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/v1/bookmark")
public class BookmarkController {

    @Resource
    private BookmarkRepository bookmarkRepository;

    @GetMapping("/add/{symbol}")
    public BookmarkStock addBookmark(@PathVariable(name = "symbol") String symbol) {
        return bookmarkRepository.save(BookmarkStock.builder().stock(symbol).build());
    }

    @PostMapping(value = "/remove")
    public void removeBookmark(@RequestBody String symbol) {
        bookmarkRepository.delete(BookmarkStock.builder().stock(symbol).build());
    }

    @GetMapping(value = "/list")
    public Iterable<BookmarkStock> listBookmarks() {
        return bookmarkRepository.findAll();
    }
}
