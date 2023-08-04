package com.hazelcast.controller;

import com.hazelcast.entity.UserAccount;
import com.hazelcast.repositories.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hazelcast.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class UserAccountController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @PostMapping("/add")
    @CacheEvict(value = "UserAccount", allEntries = true)
    public void createUser(@RequestBody UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @GetMapping("/get")
    @Cacheable("UserAccount")
    public ResponseEntity<List<UserAccount>> fetchAllUserDetails() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        log.info("fetchAllUserDetails {}", userAccounts);
        return ResponseEntity.
                status(HttpStatus.OK).body(userAccounts);
    }

    @GetMapping(path = {"/get/{id}"})
    @Cacheable(value = "UserAccount", key = "#id")
    public ResponseEntity<Optional<UserAccount>> getUser(@PathVariable("id") long id) throws InterruptedException {
        Optional<UserAccount> userAccount = Optional.ofNullable(userAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with this Id doesn't exists in the databases!")));
        log.info("getUser {}:", userAccount);
        return ResponseEntity.ok().body(userAccount);
    }


    @DeleteMapping(path = {"/{id}"})
    @CacheEvict(value = "UserAccount", allEntries = true)
    public String deleteUser(@PathVariable("id") long id) {
        //remove from both cache and database
        userAccountRepository.deleteById(id);
        log.info("deleteUser {}", id);
        return "deleted !";
    }


}