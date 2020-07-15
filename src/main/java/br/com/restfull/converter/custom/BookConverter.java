package br.com.restfull.converter.custom;

import br.com.restfull.data.model.Book;
import br.com.restfull.data.dto.BookDTO;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {

    public BookDTO convertEntityToVO(Book book) {
        BookDTO vo = new BookDTO();
        vo.setKey(book.getId());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(book.getLaunchDate());
        vo.setPrice(book.getPrice());
        vo.setTitle(book.getTitle());
        return vo;
    }

    public Book convertVOToEntity(BookDTO book) {
        Book entity = new Book();
        entity.setId(book.getKey());
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        return entity;
    }
}
