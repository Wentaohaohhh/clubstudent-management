package com.example.clubstudentmanagement.service;

import com.example.clubstudentmanagement.dao.ClubDao;
import com.example.clubstudentmanagement.exceptions.InvalidClubException;
import com.example.clubstudentmanagement.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ClubService {

    ClubDao clubDao;

    @Autowired
    public ClubService(ClubDao clubDao) {
        this.clubDao = clubDao;
    }

    public List<Club> getAllClubs(){
        return (List<Club>) clubDao.findAll();
    }

    public Club addClub(Club club){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(club.getYear() < currentYear){
            throw new InvalidClubException("Cannot add class in the past");
        }
        if(club.getYear() > currentYear + 1){
            throw new InvalidClubException("Cannot add class in the far future");
        }
        return clubDao.save(club);
    }
}
