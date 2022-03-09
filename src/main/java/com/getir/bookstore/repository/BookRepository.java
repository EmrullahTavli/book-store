package com.getir.bookstore.repository;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.web.model.dto.BookCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findBookBy(BookCriteria criteria);
}
