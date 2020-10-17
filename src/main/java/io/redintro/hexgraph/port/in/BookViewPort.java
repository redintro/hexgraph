package io.redintro.hexgraph.port.in;

import io.redintro.hexgraph.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookViewPort {
    List<Book> showAll();
    Book create(Book book);
    Book read(UUID id);
    Book update(Book book);
    boolean delete(UUID id);
    long count();
}
