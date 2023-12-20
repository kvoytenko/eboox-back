package com.example.ebooxapi.controller;

import com.example.ebooxapi.data.BookDTO;
import com.example.ebooxapi.model.Book;
import com.example.ebooxapi.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        bookRepository.save(book);

        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/delete/{bookId}")
    public ResponseEntity<?> delete(@PathVariable String bookId){
        bookRepository.deleteById(Integer.valueOf(bookId));

        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/update/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody @Valid BookDTO bookDTO, @PathVariable String bookId){
        Book book = bookRepository.findById(Integer.parseInt(bookId)).get();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        bookRepository.save(book);

        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable String bookId){
        return new ResponseEntity<>(bookRepository.findById(Integer.valueOf(bookId)), HttpStatus.OK);
    }
}
