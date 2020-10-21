package io.redintro.hexgraph.port.out;

import io.redintro.hexgraph.domain.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookStorePort {
    List<Book> findAll();
    Book create(Book book);
    Book read(UUID id);
    Book update(Book book);
    boolean delete(UUID id);
    long count();
}
