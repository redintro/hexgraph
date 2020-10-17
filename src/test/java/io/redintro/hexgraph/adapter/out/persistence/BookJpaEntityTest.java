package io.redintro.hexgraph.adapter.out.persistence;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookJpaEntityTest {
    @Test
    public void shouldCreateBookJpaEntityNoParams() {
       BookJpaEntity bookJpaEntity = new BookJpaEntity();

       assertThat(bookJpaEntity, is(notNullValue()));
       assertThat(bookJpaEntity.getId(), is(nullValue()));
       assertThat(bookJpaEntity.getTitle(), is(nullValue()));
       assertThat(bookJpaEntity.getPageCount(), is(0));
       assertThat(bookJpaEntity.getAuthor(), is(nullValue()));
    }

    @Test
    public void shouldCreateBookJpaEntityWithoutId() {
        BookJpaEntity bookJpaEntity = new BookJpaEntity(bookJpaEntityFixture().getTitle(),
                bookJpaEntityFixture().getIsbn(), bookJpaEntityFixture().getPageCount(),
                bookJpaEntityFixture().getAuthor());

        assertThat(bookJpaEntity.getId(), is(nullValue()));
        assertThat(bookJpaEntity.getTitle(), is(equalTo(bookJpaEntityFixture().getTitle())));
        assertThat(bookJpaEntity.getIsbn(), is(equalTo(bookJpaEntityFixture().getIsbn())));
        assertThat(bookJpaEntity.getPageCount(), is(equalTo(bookJpaEntityFixture().getPageCount())));
        assertThat(bookJpaEntity.getAuthor().getId(), is(equalTo(bookJpaEntityFixture().getAuthor().getId())));
        assertThat(bookJpaEntity.getAuthor().getFirstName(), is(equalTo(bookJpaEntityFixture().getAuthor().getFirstName())));
        assertThat(bookJpaEntity.getAuthor().getLastName(), is(equalTo(bookJpaEntityFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldCreateBookJpaEntity() {
        BookJpaEntity bookJpaEntity = bookJpaEntityFixture();

        assertThat(bookJpaEntity.getId(), is(equalTo(bookJpaEntityFixture().getId())));
        assertThat(bookJpaEntity.getTitle(), is(equalTo(bookJpaEntityFixture().getTitle())));
        assertThat(bookJpaEntity.getIsbn(), is(equalTo(bookJpaEntityFixture().getIsbn())));
        assertThat(bookJpaEntity.getPageCount(), is(equalTo(bookJpaEntityFixture().getPageCount())));
        assertThat(bookJpaEntity.getAuthor().getId(), is(equalTo(bookJpaEntityFixture().getAuthor().getId())));
        assertThat(bookJpaEntity.getAuthor().getFirstName(), is(equalTo(bookJpaEntityFixture().getAuthor().getFirstName())));
        assertThat(bookJpaEntity.getAuthor().getLastName(), is(equalTo(bookJpaEntityFixture().getAuthor().getLastName())));
    }

    private BookJpaEntity bookJpaEntityFixture() {
        return new BookJpaEntity(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"),
                "Americana", "0123456789-10", 367,
                new AuthorJpaEntity(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                        "Don", "DeLillo"));
    }
}
