package br.com.restfull.controller;

import br.com.restfull.data.vo.v1.BookVO;
import br.com.restfull.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Book Crud Endpoints")
@RestController
@RequestMapping("/api/books/v1")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "fetch book data")
    public BookVO findById(@PathVariable("id") Long id) {
        BookVO bookVO = service.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "returns all books")
    public List<BookVO> findAll() {
        List<BookVO> books = service.findAll();
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
    public BookVO create(@RequestBody BookVO book) {
        BookVO bookVO = service.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    @ApiOperation(value = "updates a book field")
    public BookVO update(@RequestBody BookVO book) {
        BookVO bookVO = service.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "erases book's data")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
