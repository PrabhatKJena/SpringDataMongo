package edu.pk.test;

import com.mongodb.DBCollection;
import edu.pk.mongo.model.Book;
import edu.pk.mongo.repository.BookRepository;
import edu.pk.spring.config.SpringConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Main {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        // Getting bean of Main class from Spring container. This can be same as Controller OR Service bean
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Main mainBean = context.getBean(Main.class);

        mainBean.testBasic();
        mainBean.testQueryAndCriteriaAPI();
        mainBean.testRepository();
    }

    private void testRepository(){
        System.out.println("Using findOne() : ");
        Book bookOne = bookRepository.findOne("ID001");
        System.out.println(bookOne.getId()+" "+bookOne.getName()+" "+bookOne.getAuthor()+" "+
                bookOne.getCategory()+" "+bookOne.getPrice());

        System.out.println("Find By Category : ");
        List<Book> bookList = bookRepository.findByCategory("Technical");
        for(Book book:bookList){
            System.out.println(book.getId()+" "+book.getName()+" "+book.getAuthor()+" "+book.getCategory()
                    +" "+book.getPrice());
        }
    }
    private void testBasic(){
        // Using normal MongoDB API
        DBCollection booksCollection = mongoTemplate.getCollection("books");
        System.out.println("Total document Count : " + booksCollection.count());
    }

    private void testQueryAndCriteriaAPI(){
        // Using Spring's Query and Criteria API
        // Query all documents where name  = Spring In Action
        Query query = new Query(Criteria.where("name").is("Spring In Action"));
        List<Book> bookList = mongoTemplate.find(query, Book.class);
        System.out.println("List of All Documents where name = Think In Java : ");
        for(Book book:bookList){
            System.out.println(book.getId()+" "+book.getName()+" "+book.getAuthor()+" "+book.getCategory()
                    +" "+book.getPrice());
        }
    }
}

