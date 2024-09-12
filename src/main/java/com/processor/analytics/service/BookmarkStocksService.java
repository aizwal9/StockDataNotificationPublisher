package com.processor.analytics.service;

import com.processor.analytics.models.BookmarkStock;
import com.processor.analytics.repository.BookmarkRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkStocksService {

    @Resource
    private BookmarkRepository bookmarkRepository;

    public List<BookmarkStock> getAll(){
        return bookmarkRepository.findAll();
    }
}
