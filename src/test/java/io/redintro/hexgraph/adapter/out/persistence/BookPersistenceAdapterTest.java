package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.model.Author;
import io.redintro.hexgraph.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookPersistenceAdapterTest {
    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookPersistenceAdapter bookPersistenceAdapter;

    @Test
    public void shouldFindAll() {
        when(repository.findAll()).thenReturn(List.of(bookJpaEntityFixture()));

        List<Book> books = bookPersistenceAdapter.findAll();

        assertThat(books.size(), is(equalTo(1)));
    }

    @Test
    public void shouldCreate() {
        when(repository.save(any(BookJpaEntity.class))).thenReturn(bookJpaEntityFixture());

        Book book = bookPersistenceAdapter.create(bookFixture());

        assertThat(book.getId(), is(equalTo(bookFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldRead() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(bookJpaEntityFixture()));

        Book book = bookPersistenceAdapter.read(bookFixture().getId());

        assertThat(book.getId(), is(equalTo(bookFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldUpdate() {
        when(repository.save(any(BookJpaEntity.class))).thenReturn(bookJpaEntityFixture());

        Book book = bookPersistenceAdapter.update(bookFixture());

        assertThat(book.getId(), is(equalTo(bookFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldDelete() {
        doNothing().when(repository).deleteById(any(UUID.class));

        boolean isDeleted = bookPersistenceAdapter.delete(bookFixture().getId());

        assertThat(isDeleted, is(equalTo(true)));
    }

    @Test
    public void shouldCount() {
        when(repository.count()).thenReturn(1L);

        long count = bookPersistenceAdapter.count();

        assertThat(count, is(equalTo(1L)));
    }

    private BookJpaEntity bookJpaEntityFixture() {
        return new BookJpaEntity(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"), "Americana", "0123456789-10", 367,
                new AuthorJpaEntity(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"), "Don", "DeLillo"));
    }

    private Book bookFixture() {
        return new Book(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"), "Americana", "0123456789-10", 367,
                new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"), "Don", "DeLillo"));
    }
}
