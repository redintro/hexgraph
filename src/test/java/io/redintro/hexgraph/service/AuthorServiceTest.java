package io.redintro.hexgraph.service;

import io.redintro.hexgraph.domain.Author;

import java.util.UUID;

import io.redintro.hexgraph.port.out.AuthorStorePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorStorePort authorStorePort;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void shouldFindAll() {
        when(authorStorePort.findAll()).thenReturn(List.of(authorFixture()));

        List<Author> authors = authorService.showAll();

        assertThat(authors.size(), is(equalTo(1)));
    }

    @Test
    public void shouldCreate() {
        when(authorStorePort.create(any(Author.class))).thenReturn(authorFixture());

        Author author = authorService.create(authorFixture());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldRead() {
        when(authorStorePort.read(any(UUID.class))).thenReturn(authorFixture());

        Author author = authorService.read(authorFixture().getId());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldUpdate() {
        when(authorStorePort.update(any(Author.class))).thenReturn(authorFixture());

        Author author = authorService.update(authorFixture());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldDelete() {
        when(authorStorePort.delete(any(UUID.class))).thenReturn(true);

        boolean isDeleted = authorService.delete(authorFixture().getId());

        assertThat(isDeleted, is(equalTo(true)));
    }

    @Test
    public void shouldCount() {
        when(authorStorePort.count()).thenReturn(1L);

        long count = authorService.count();

        assertThat(count, is(equalTo(1L)));
    }

    private Author authorFixture() {
        return new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                "Don", "DeLillo");
    }
}
