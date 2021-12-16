package com.jm.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jm.bookclub.models.Book;
import com.jm.bookclub.respositories.BookRepository;



@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;

	// Saves Book
	public Book saveBook(Book b) {
		return bookRepo.save(b);
	}

	// Displays All Books
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	// Displays One Book
	public Book findOneBook(Long id) {
		Optional<Book> findOne = bookRepo.findById(id);
		if(findOne.isPresent()) {
			return findOne.get();
		} else {
			return null;
		}

	}

	// Deletes one book
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
		
	}
	

}
