package io.redintro.hexgraph.adapter.out.persistence;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "book")
public class BookJpaEntity {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="isbn", nullable = false)
    private String isbn;

    @Column(name="page_count", nullable = false)
    private int pageCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author" )
    private AuthorJpaEntity author;

    public BookJpaEntity() {
    }

    public BookJpaEntity(String title, String isbn, int pageCount, AuthorJpaEntity author) {
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.author = author;
    }

    public BookJpaEntity(UUID id, String title, String isbn, int pageCount, AuthorJpaEntity author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public AuthorJpaEntity getAuthor() {
        return author;
    }
}
