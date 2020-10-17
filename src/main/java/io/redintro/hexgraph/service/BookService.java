package io.redintro.hexgraph.service;

import io.redintro.hexgraph.domain.Book;
import io.redintro.hexgraph.port.in.BookViewPort;
import io.redintro.hexgraph.port.out.BookStorePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService implements BookViewPort {
    private final BookStorePort bookStorePort;

    public BookService(BookStorePort bookStorePort) {
        this.bookStorePort = bookStorePort;
    }

    @Override
    public List<Book> showAll() {
        return bookStorePort.findAll();
    }

    @Override
    public Book create(Book book) {
        return bookStorePort.create(book);
    }

    @Override
    public Book read(UUID id) {
        return bookStorePort.read(id);
    }

    @Override
    public Book update(Book book) {
        return bookStorePort.update(book);
    }

    @Override
    public boolean delete(UUID id) {
        return bookStorePort.delete(id);
    }

    @Override
    public long count() {
        return bookStorePort.count();
    }
}
