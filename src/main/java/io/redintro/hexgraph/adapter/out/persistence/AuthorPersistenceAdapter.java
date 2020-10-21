package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.model.Author;
import io.redintro.hexgraph.port.out.AuthorStorePort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AuthorPersistenceAdapter implements AuthorStorePort {
    private final AuthorRepository repository;

    public AuthorPersistenceAdapter(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> findAll() {
        List<AuthorJpaEntity> authors = (List<AuthorJpaEntity>) repository.findAll();

        return authors
                .stream()
                .map(AuthorOutMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Author create(Author author) {
        AuthorJpaEntity authorJpaEntity = AuthorOutMapper.mapToJpaEntity(author);
        repository.save(authorJpaEntity);
        return AuthorOutMapper.mapToDomainEntity(authorJpaEntity);
    }

    @Override
    public Author read(UUID id) {
        return repository.findById(id)
                .map(AuthorOutMapper::mapToDomainEntity)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author update(Author author) {
        AuthorJpaEntity authorJpaEntity = AuthorOutMapper.mapToJpaEntity(author);
        repository.save(authorJpaEntity);
        return AuthorOutMapper.mapToDomainEntity(authorJpaEntity);
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
