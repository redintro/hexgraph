package io.redintro.hexgraph.adapter.out.persistence;

import io.redintro.hexgraph.domain.model.Author;

public class AuthorOutMapper {
    public static Author mapToDomainEntity(AuthorJpaEntity author) {
        return new Author(
                author.getId(),
                author.getFirstName(),
                author.getLastName());
    }

    public static AuthorJpaEntity mapToJpaEntity(Author author) {
        return new AuthorJpaEntity(
                author.getId(),
                author.getFirstName(),
                author.getLastName());
    }
}
