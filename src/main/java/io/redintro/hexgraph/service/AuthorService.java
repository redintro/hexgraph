package io.redintro.hexgraph.service;

import io.redintro.hexgraph.domain.Author;
import io.redintro.hexgraph.port.in.AuthorViewPort;
import io.redintro.hexgraph.port.out.AuthorStorePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService implements AuthorViewPort {
    private final AuthorStorePort authorStorePort;

    public AuthorService(AuthorStorePort authorStorePort) {
        this.authorStorePort = authorStorePort;
    }

    @Override
    public List<Author> showAll() {
        return authorStorePort.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorStorePort.create(author);
    }

    @Override
    public Author read(UUID id) {
        return authorStorePort.read(id);
    }

    @Override
    public Author update(Author author) {
        return authorStorePort.update(author);
    }

    @Override
    public boolean delete(UUID id) {
        return authorStorePort.delete(id);
    }

    @Override
    public long count() {
        return authorStorePort.count();
    }
}
