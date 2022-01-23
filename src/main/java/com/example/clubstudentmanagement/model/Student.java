package com.example.clubstudentmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public String toString() {
        String str = "";
        str += "PrimaryID: " + getId();
        str += " Name: " + getName();
        return str;
    }
}
