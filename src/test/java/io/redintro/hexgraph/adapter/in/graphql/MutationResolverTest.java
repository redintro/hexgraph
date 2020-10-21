package io.redintro.hexgraph.adapter.in.graphql;
import io.redintro.hexgraph.domain.model.Author;
import io.redintro.hexgraph.domain.model.Book;
import io.redintro.hexgraph.port.in.AuthorViewPort;
import io.redintro.hexgraph.port.in.BookViewPort;
import io.redintro.hexgraph.shared.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MutationResolverTest {

    @Mock
    private AuthorViewPort authorViewPort;

    @Mock
    private BookViewPort bookViewPort;

    @InjectMocks
    private MutationResolver mutationResolver;

    @Test
    public void shouldCreateAuthor() {
        when(authorViewPort.create(any(Author.class))).thenReturn(authorFixture());

        Author author = mutationResolver.newAuthor(authorFixture().getFirstName(), authorFixture().getLastName());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
    }

    @Test
    public void shouldCreateBook() {
        when(bookViewPort.create(any(Book.class))).thenReturn(bookFixture());

        Book book = mutationResolver.newBook(bookFixture().getTitle(), bookFixture().getIsbn(),
                bookFixture().getPageCount(), bookFixture().getAuthor().getId());

        assertThat(book.getId(), is(equalTo(bookFixture().getId())));
        assertThat(book.getTitle(), is(equalTo(bookFixture().getTitle())));
        assertThat(book.getIsbn(), is(equalTo(bookFixture().getIsbn())));
        assertThat(book.getPageCount(), is(equalTo(bookFixture().getPageCount())));
        assertThat(book.getAuthor().getId(), is(equalTo(bookFixture().getAuthor().getId())));
        assertThat(book.getAuthor().getFirstName(), is(equalTo(bookFixture().getAuthor().getFirstName())));
        assertThat(book.getAuthor().getLastName(), is(equalTo(bookFixture().getAuthor().getLastName())));
    }

    @Test
    public void shouldDeleteBook() {
        when(bookViewPort.delete(any(UUID.class))).thenReturn(true);

        boolean isDeleted = mutationResolver.deleteBook(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"));

        assertThat(isDeleted, is(equalTo(true)));
    }

    @Test
    public void shouldUpdateBookPageCount() {
        when(bookViewPort.read(any(UUID.class))).thenReturn(bookFixture());
        when(bookViewPort.update(any(Book.class))).thenReturn(new Book(bookFixture().getId(), bookFixture().getTitle(),
                bookFixture().getIsbn(), 368, bookFixture().getAuthor()));

        Book book = mutationResolver.updateBookPageCount(368,
                UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"));

        assertThat(book.getPageCount(), is(equalTo(368)));
    }

    @Test
    public void shouldThrowExceptionOnUpdateBookPageCount() {
        when(bookViewPort.read(any(UUID.class))).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> {
            mutationResolver.updateBookPageCount(368,
                    UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"));
        });
    }

    private Author authorFixture() {
        return new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                "Don", "DeLillo");
    }

    private Book bookFixture() {
        return new Book(UUID.fromString("bde85949-c3c7-46d1-a9f2-b515c9dcf9fd"),
                "Americana", "0123456789-10", 367,
                new Author(UUID.fromString("0a19d1c8-b231-4993-9c01-54332228e4ca"),
                        "Don", "DeLillo"));
    }
}
