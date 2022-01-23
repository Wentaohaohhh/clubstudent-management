package com.example.clubstudentmanagement.dao;

import com.example.clubstudentmanagement.model.Club;
import org.springframework.data.repository.CrudRepository;

public interface ClubDao extends CrudRepository<Club, Long> {
}
