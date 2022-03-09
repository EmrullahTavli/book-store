package com.getir.bookstore.web.mapper;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.web.model.dto.BookCriteria;
import com.getir.bookstore.web.model.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book map(BookDto bookDto);

    BookDto map(Book book);

    BookCriteria mapCriteria(BookDto bookDto);
}
