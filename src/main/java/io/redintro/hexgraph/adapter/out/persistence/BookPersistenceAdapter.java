package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.Book;
import io.redintro.hexgraph.port.out.BookStorePort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookPersistenceAdapter implements BookStorePort {
    private final BookRepository repository;

    public BookPersistenceAdapter(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAll() {
        List<BookJpaEntity> book = (List<BookJpaEntity>) repository.findAll();

        return book
                .stream()
                .map(BookOutMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Book create(Book book) {
        BookJpaEntity bookJpaEntity = BookOutMapper.mapToJpaEntity(book);
        repository.save(bookJpaEntity);
        return BookOutMapper.mapToDomainEntity(bookJpaEntity);
    }

    @Override
    public Book read(UUID id) {
        return repository.findById(id)
                .map(BookOutMapper::mapToDomainEntity)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book update(Book book) {
        BookJpaEntity bookJpaEntity = BookOutMapper.mapToJpaEntity(book);
        repository.save(bookJpaEntity);
        return BookOutMapper.mapToDomainEntity(bookJpaEntity);
    }

    @Override
    public boolean delete(UUID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public long count() {
        return repository.count();
    }
}

