package com.example.sping_portfolio.model.SQL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
Extends the JpaRepository interface from Spring Data JPA.
-- Java Persistent API (JPA) - Hibernate: map, store, update and retrieve data
-- JpaRepository defines standard CRUD methods
-- Via JPA the developer can retrieve data from relational databases to Java objects and vice versa.
 */
public interface PersonJpaRepository extends JpaRepository<Person, Long> {
//    @Query("SELECT u FROM Person u WHERE u.email = ?1")
//    public Person findByEmail(String email);
}