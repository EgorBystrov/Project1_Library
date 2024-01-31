package org.springLibrary.dao;

import org.springLibrary.models.Book;
import org.springLibrary.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    //
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, publication_year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getPublicationYear());
    }
    //
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, publication_year=? WHERE book_id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getPublicationYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
    public List<Book> getBooksByPersonId(int personId){
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }
    public Optional<Person> getBookOwner(int personId){
        return jdbcTemplate.query(
                "SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.person_id " +
                        "WHERE Book.book_id = ?", new Object[]{personId}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void release(int bookId){
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", bookId);
    }
    public void assign(int bookId, Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", selectedPerson.getPersonId(), bookId);
    }

}
