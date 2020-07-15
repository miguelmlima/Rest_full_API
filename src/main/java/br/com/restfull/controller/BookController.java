package br.com.restfull.controller;

import br.com.restfull.data.dto.BookDTO;
import br.com.restfull.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = "Book Crud Endpoints")
@RestController
@RequestMapping("/api/books/v1")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "fetch book data by your ID")
    public BookDTO findById(@PathVariable("id") Long id) {
        BookDTO bookDTO = service.findById(id);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookDTO;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "returns all books")
    public List<BookDTO> findAll() {
        List<BookDTO> books = service.findAll();
        books
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()
                        )
                );
        return books;
    }

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "register a book")
    public BookDTO create(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.create(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return bookDTO;
    }

    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "updates a book field by your ID")
    public BookDTO update(@RequestBody BookDTO book) {
        BookDTO bookDTO = service.update(book);
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getKey())).withSelfRel());
        return bookDTO;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "erases book's data by your ID")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
