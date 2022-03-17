package com.example.sping_portfolio.model.SQL;

import lombok.*;
import org.hibernate.annotations.Type;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
     */
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @SuppressWarnings("JpaAttributeTypeInspection")
    private JSONObject taskList;

    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addTask(JSONObject task, long unixDate) {
        JSONArray array = new JSONArray();
        array.add(task);
        taskList.put(unixDate, array);
    }



    /*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min=5)
    @Email
    private String email;
     */

    /*
    @NonNull: Places this in @RequiredArgsConstructor
    @Size(min=2, max=30): Allows names between 2 and 30 characters long.
     */
    /*
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
     */
    /* Initializer used when setting data from an API */
    /*
    public Person(String email, String name, Date dob) {
        this.email = email;
        this.name = name;
        this.dob = dob;
    }
     */
    /* A custom getter to return age from dob calculation */
    /*
    public int getAge() {
        LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthDay, LocalDate.now()).getYears();
    }
    */
}