package io.redintro.hexgraph.adapter.in.graphql;

import io.redintro.hexgraph.domain.Author;
import io.redintro.hexgraph.domain.Book;
import io.redintro.hexgraph.port.in.AuthorViewPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookResolverTest {

    @Mock
    private AuthorViewPort authorViewPort;

    @InjectMocks
    private BookResolver bookResolver;

    @Test
    public void shouldGetAuthor() {
        when(authorViewPort.read(any(UUID.class))).thenReturn(authorFixture());

        Author author = bookResolver.getAuthor(bookFixture());

        assertThat(author.getId(), is(equalTo(authorFixture().getId())));
        assertThat(author.getFirstName(), is(equalTo("Don")));
        assertThat(author.getLastName(), is(equalTo("DeLillo")));
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


