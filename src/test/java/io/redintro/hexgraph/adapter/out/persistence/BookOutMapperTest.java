package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.model.Author;
import io.redintro.hexgraph.domain.model.Book;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookOutMapperTest {
    @Test
    public void shouldCreateBookOutputMapper() {
        BookOutMapper bookOutMapper = new BookOutMapper();

        assertThat(bookOutMapper, is(notNullValue()));
    }

    @Test
    public void shouldMapToDomainEntity() {
        BookJpaEntity bookJpaEntity = bookJpaEntityFixture();

        Book book = BookOutMapper.mapToDomainEntity(bookJpaEntity);

        assertThat(book.getId(), is(equalTo(bookJpaEntityFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookJpaEntityFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookJpaEntityFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookJpaEntityFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookJpaEntityFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookJpaEntityFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookJpaEntityFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldMapToJpaEntity() {
        Book book = bookFixture();

        BookJpaEntity bookJpaEntity = BookOutMapper.mapToJpaEntity(book);

        assertThat(bookJpaEntity.getId(), is(equalTo(bookFixture().getId())));
        assertThat(bookJpaEntity.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(bookJpaEntity.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(bookJpaEntity.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(bookJpaEntity.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(bookJpaEntity.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(bookJpaEntity.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
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
