package com.processor.analytics.controller;

import com.processor.analytics.models.BookmarkStock;
import com.processor.analytics.repository.BookmarkRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bookmark")
public class BookmarkController {

    @Resource
    private BookmarkRepository bookmarkRepository;

    @GetMapping("/add")
    public BookmarkStock addBookmark(@RequestBody BookmarkStock bookmarkStock) {
        BookmarkStock existingBookmarkStock = bookmarkRepository.findByStock(bookmarkStock.getStock());
        if (existingBookmarkStock != null) {
            existingBookmarkStock.setAmount(bookmarkStock.getAmount());
            return bookmarkRepository.save(existingBookmarkStock);
        } else {
            return bookmarkRepository.save(bookmarkStock);
        }
    }

    @PostMapping(value = "/remove/{symbol}")
    public void removeBookmark(@PathVariable(name = "symbol") String symbol) {
        bookmarkRepository.deleteBookmarkStockByStock(symbol);
    }

    @GetMapping(value = "/list")
    public List<BookmarkStock> listBookmarks() {
        return bookmarkRepository.findAll();
    }
}
