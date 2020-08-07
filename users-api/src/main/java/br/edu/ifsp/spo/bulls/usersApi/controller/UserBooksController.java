package br.edu.ifsp.spo.bulls.usersApi.controller;

import br.edu.ifsp.spo.bulls.usersApi.dto.BookCaseTO;
import br.edu.ifsp.spo.bulls.usersApi.dto.UserBookUpdateStatusTO;
import br.edu.ifsp.spo.bulls.usersApi.dto.UserBooksTO;
import br.edu.ifsp.spo.bulls.usersApi.service.UserBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookcases")
@CrossOrigin(origins = "*")
public class UserBooksController {
    @Autowired
    private UserBooksService service;

    @PostMapping
    public UserBooksTO post(@RequestBody UserBooksTO dto) {
        return service.save(dto);
    }

    @GetMapping("/profile/{profileId}")
    public BookCaseTO getAllByProfile(@PathVariable int profileId) {
        return service.getByProfileId(profileId);
    }

    @PutMapping("/status")
    public UserBooksTO putStatus(@RequestBody UserBookUpdateStatusTO dto) {
        return service.updateStatus(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}