package com.example.clubstudentmanagement.api;

import com.example.clubstudentmanagement.exceptions.InvalidClubException;
import com.example.clubstudentmanagement.model.Club;
import com.example.clubstudentmanagement.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/club")
public class ClubController {

    private ClubService clubService;

    @Autowired

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    List<Club> getAllClubs(){
        return clubService.getAllClubs();
    }

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String> addClub(@RequestBody Club club){
        try{
            Club savedClub = clubService.addClub(club);
            return ResponseEntity.ok("New Club Added. " + savedClub.toString());
        }catch (InvalidClubException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
