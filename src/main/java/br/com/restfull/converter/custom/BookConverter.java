package br.com.restfull.converter.custom;

import br.com.restfull.data.model.Book;
import br.com.restfull.data.vo.v1.BookVO;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {

    public BookVO convertEntityToVO(Book book) {
        BookVO vo = new BookVO();
        vo.setKey(book.getId());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(book.getLaunchDate());
        vo.setPrice(book.getPrice());
        vo.setTitle(book.getTitle());
        return vo;
    }

    public Book convertVOToEntity(BookVO book) {
        Book entity = new Book();
        entity.setId(book.getKey());
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        return entity;
    }
}
