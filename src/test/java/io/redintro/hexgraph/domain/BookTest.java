package io.redintro.hexgraph.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookTest {
    @Test
    public void shouldCreateBookWithoutId() {
        Book book = new Book(bookFixture().getTitle(), bookFixture().getIsbn(), bookFixture().getPageCount(),
                bookFixture().getAuthor());

        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldCreateABookWithAnId() {
        Book book = bookFixture();

        assertThat(book.getId(), is(equalTo(bookFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldCreateABookWithoutAnId() {
        Book book = bookFixture();

        assertThat(book.getId(), is(equalTo(bookFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldToString() {
        Book book = bookFixture();
        String bookToString = "Book{" +
                "id=" + bookFixture().getId() +
                ", title='" + bookFixture().getTitle() + '\'' +
                ", isbn='" + bookFixture().getIsbn() + '\'' +
                ", pageCount=" + bookFixture().getPageCount() +
                ", author=" + bookFixture().getAuthor() +
                '}';

        assertThat(book.toString(), is(equalTo(bookToString)));
    }

    private Book bookFixture() {
        return new Book(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"),
                "Americana", "0123456789-10", 367,
                new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                        "Don", "DeLillo"));
    }
}
