package io.redintro.hexgraph.port.in;

import io.redintro.hexgraph.domain.model.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorViewPort {
    List<Author> showAll();
    Author create(Author author);
    Author read(UUID id);
    Author update(Author author);
    boolean delete(UUID id);
    long count();
}
