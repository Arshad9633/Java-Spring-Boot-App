package com.bookMangement.bookManagement.repository;

import com.bookMangement.bookManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.genre = NULL WHERE b.genre.id = :genreId")
    void setGenreToNull(int genreId);


    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.publisher = NULL WHERE b.publisher.id = :publisherId")
    void setPublisherToNull(int publisherId);
}
