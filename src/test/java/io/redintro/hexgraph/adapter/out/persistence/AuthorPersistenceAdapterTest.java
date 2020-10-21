package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.model.Author;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorPersistenceAdapterTest {
    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorPersistenceAdapter authorPersistenceAdapter;

    @Test
    public void shouldFindAll() {
        when(repository.findAll()).thenReturn(List.of(authorJpaEntityFixture()));

        List<Author> authors = authorPersistenceAdapter.findAll();

        assertThat(authors.size(), is(equalTo(1)));
    }

    @Test
    public void shouldCreate() {
        when(repository.save(any(AuthorJpaEntity.class))).thenReturn(authorJpaEntityFixture());

        Author author = authorPersistenceAdapter.create(authorFixture());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldRead() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(authorJpaEntityFixture()));

        Author author = authorPersistenceAdapter.read(authorFixture().getId());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldUpdate() {
        when(repository.save(any(AuthorJpaEntity.class))).thenReturn(authorJpaEntityFixture());

        Author author = authorPersistenceAdapter.update(authorFixture());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldDelete() {
        doNothing().when(repository).deleteById(any(UUID.class));

        boolean isDeleted = authorPersistenceAdapter.delete(authorFixture().getId());

        assertThat(isDeleted, is(equalTo(true)));
    }

    @Test
    public void shouldCount() {
        when(repository.count()).thenReturn(1L);

        long count = authorPersistenceAdapter.count();

        assertThat(count, is(equalTo(1L)));
    }

    private AuthorJpaEntity authorJpaEntityFixture() {
        return new AuthorJpaEntity(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"), "Don", "DeLillo");
    }

    private Author authorFixture() {
        return new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"), "Don", "DeLillo");
    }
}
