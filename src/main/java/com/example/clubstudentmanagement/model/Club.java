package com.example.clubstudentmanagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    Integer year;

    @Column(nullable = false)
    Integer number;

    @OneToMany(mappedBy = "club")
    List<Student> students;

    public Club(Long id, Integer year, Integer number) {
        this.id = id;
        this.year = year;
        this.number = number;
    }

    public Club() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Primary ID: " + getId();
        str += " Year: " + getYear();
        str += " Number: " + getNumber();
        return str;
    }
}
