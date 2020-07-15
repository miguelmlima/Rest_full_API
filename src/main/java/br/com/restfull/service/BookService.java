package br.com.restfull.service;

import br.com.restfull.ExceptionResponse.ResourceNotFoundException;
import br.com.restfull.converter.DozerConverter;
import br.com.restfull.converter.custom.BookConverter;
import br.com.restfull.data.model.Book;
import br.com.restfull.data.vo.v1.BookVO;
import br.com.restfull.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    @Autowired
    BookConverter translator;

    public BookVO create(BookVO book) {
        Book entity = DozerConverter.traslatorObject(book, Book.class);
        BookVO vo = DozerConverter.traslatorObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public BookVO update(BookVO book) {
        Book entity = repository.findById(book.getKey())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());

        BookVO vo = DozerConverter.traslatorObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);
    }

    public BookVO findById(Long id) {
        Book entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found this ID"));
        return DozerConverter.traslatorObject(entity, BookVO.class);
    }

    public List<BookVO> findAll() {
        return DozerConverter.translatorObjectList(repository.findAll(), BookVO.class);
    }
}
