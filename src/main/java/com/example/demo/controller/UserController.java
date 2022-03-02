package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsersByName(@RequestParam(required = false, name = "name") String name) {
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserById(id, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateSalary(@PathVariable("id") String id, @RequestBody User user) {
        if (user.getSalary() <= 0) {
            return ResponseEntity.badRequest().build();
        } else userService.updateSalaryByUserId(id, user.getSalary());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return ResponseEntity.accepted().build();
    }
}
