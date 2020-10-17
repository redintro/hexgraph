package io.redintro.hexgraph.service;

import io.redintro.hexgraph.domain.Author;
import io.redintro.hexgraph.domain.Book;
import io.redintro.hexgraph.port.out.BookStorePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookStorePort bookStorePort;

    @InjectMocks
    private BookService bookService;

    @Test
    public void shouldFindAll() {
        when(bookStorePort.findAll()).thenReturn(List.of(bookFixture()));

        List<Book> books = bookService.showAll();

        assertThat(books.size(), is(equalTo(1)));
    }

    @Test
    public void shouldCreate() {
        when(bookStorePort.create(any(Book.class))).thenReturn(bookFixture());

        Book book = bookService.create(bookFixture());

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
        when(bookStorePort.read(any(UUID.class))).thenReturn(bookFixture());

        Book book = bookService.read(bookFixture().getId());

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
        when(bookStorePort.update(any(Book.class))).thenReturn(bookFixture());

        Book book = bookService.update(bookFixture());

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
        when(bookStorePort.delete(any(UUID.class))).thenReturn(true);

        boolean isDeleted = bookService.delete(bookFixture().getId());

        assertThat(isDeleted, is(equalTo(true)));
    }

    @Test
    public void shouldCount() {
        when(bookStorePort.count()).thenReturn(1L);

        long count = bookService.count();

        assertThat(count, is(equalTo(1L)));
    }

    private Book bookFixture() {
        return new Book(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"),
                "Americana", "0123456789-10", 367,
                new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                        "Don", "DeLillo"));
    }
}
