package com.jm.bookclub.respositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jm.bookclub.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	
    
    Optional<Book> findById(Long id);
    
    List<Book> findAll();
    
    void deleteById(Long id);

}
