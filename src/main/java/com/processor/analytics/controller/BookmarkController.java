package com.processor.analytics.controller;

import com.processor.analytics.models.BookmarkStock;
import com.processor.analytics.repository.BookmarkRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/bookmark")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class BookmarkController {

    @Resource
    private BookmarkRepository bookmarkRepository;

    @PostMapping("/add")
    public BookmarkStock addBookmark(@RequestBody BookmarkStock bookmarkStock) {
        BookmarkStock existingBookmarkStock = bookmarkRepository.findByStock(bookmarkStock.getStock());
        if (existingBookmarkStock != null) {
            existingBookmarkStock.setAmount(bookmarkStock.getAmount());
            return bookmarkRepository.save(existingBookmarkStock);
        } else {
            return bookmarkRepository.save(bookmarkStock);
        }
    }

    @DeleteMapping(value = "/remove/{symbol}")
    public void removeBookmark(@PathVariable(name = "symbol") String symbol) {
        bookmarkRepository.deleteBookmarkStockByStock(symbol);
    }

    @GetMapping(value = "/list")
    public List<BookmarkStock> listBookmarks() {
        return bookmarkRepository.findAll();
    }
}
