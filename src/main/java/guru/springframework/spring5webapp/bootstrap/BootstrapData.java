package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher pub = new Publisher("Happy 420 Times", "111 main st", "Boca Raton", "FL", "33487");

        publisherRepository.save(pub);

        Author eric =  new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1231234");
        updateData(pub, eric, ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB","39394594599");
        updateData(pub, rod, noEJB);

        System.out.println("started Bootstrap");
        System.out.println("Number of Books" + bookRepository.count());
        System.out.println("Number of Publishers" + publisherRepository.count());
        System.out.println("Number of Publishers Books" + pub.getBooks().size());
    }

    private void updateData(Publisher publisher, Author author, Book book) {
        author.getBooks().add(book);
        publisher.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);

        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);
    }
}
