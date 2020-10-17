package io.redintro.hexgraph.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookRepository extends CrudRepository<BookJpaEntity, UUID> {
}
