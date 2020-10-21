package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.model.Book;

public class BookOutMapper {
    public static Book mapToDomainEntity(BookJpaEntity book) {
        return new Book(
            book.getId(),
            book.getTitle(),
            book.getIsbn(),
            book.getPageCount(),
            AuthorOutMapper.mapToDomainEntity(book.getAuthor())
        );
    }

    public static BookJpaEntity mapToJpaEntity(Book book) {
        return new BookJpaEntity(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPageCount(),
                AuthorOutMapper.mapToJpaEntity(book.getAuthor())
        );
    }
}
