package edu.pk.mongo.repository;

import edu.pk.mongo.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, String>{
    public List<Book> findByCategory(String category);
}

