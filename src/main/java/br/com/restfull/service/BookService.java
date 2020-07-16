package br.com.restfull.service;

import br.com.restfull.converter.DozerConverter;
import br.com.restfull.converter.custom.BookConverter;
import br.com.restfull.data.dto.BookDTO;
import br.com.restfull.data.model.Book;
import br.com.restfull.exception.ResourceNotFoundException;
import br.com.restfull.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    @Autowired
    BookConverter translator;

    public BookDTO create(BookDTO book) {
        Book entity = DozerConverter.traslatorObject(book, Book.class);
        BookDTO vo = DozerConverter.traslatorObject(repository.save(entity), BookDTO.class);
        return vo;
    }

    public BookDTO update(BookDTO book) {
        Book entity = repository.findById(book.getKey())
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());

        BookDTO vo = DozerConverter.traslatorObject(repository.save(entity), BookDTO.class);
        return vo;
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No records found this ID"));
        repository.delete(entity);
    }

    public BookDTO findById(Long id) {
        Book entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No records found this ID"));
        return DozerConverter.traslatorObject(entity, BookDTO.class);
    }

    public Page<BookDTO> findAuthorByName(String author, Pageable pageable) {
        Page<Book> page = repository.findAuthorByName(author, pageable);
        return page.map(this::convertToBookDTO);
    }

    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> page = repository.findAll(pageable);
        return page.map(this::convertToBookDTO);
    }

    private BookDTO convertToBookDTO(Book entity) {
        return DozerConverter.traslatorObject(entity, BookDTO.class);
    }
}
